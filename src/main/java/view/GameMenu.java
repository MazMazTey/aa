package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AA;
import model.Ball;
import model.Game;

public class GameMenu extends Application {
    private final Game game;
    private final GameMenuController controller;
    public GameMenu(Game game) {
        this.game = game;
        this.controller = new GameMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!AA.getLoggedInUser().isGuest()) game.setCurrentPlayer(AA.getLoggedInUser());
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));
        Scene scene = new Scene(gamePane);
        gamePane.getChildren().add(game.getCenterCircle());
        gamePane.getChildren().get(0).requestFocus();
        //Ball ball = game.initializeBall(gamePane);

        HBox hBox = new HBox();
        Text freezeCoolDown = new Text();
        Text numberOfBallsLeft = new Text("Number of balls left :" + game.getBallsLeft());
        ProgressBar progressBar = new ProgressBar(1);
        controller.createHbox(hBox , freezeCoolDown , numberOfBallsLeft , progressBar);
        gamePane.getChildren().add(hBox);
        game.getCenterCircle().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (game.isGameOver()) {
                    System.out.println("Game Over!");
                    VBox vBox = new VBox();
                    controller.result(game, vBox , game.getScore());
                    gamePane.getChildren().add(vBox);
                }
                if (keyName.equals(game.getCurrentPlayer().getShootBallKey())
                        && !game.isGameOver() && !game.isPaused()) {
                    Ball ball = game.initializeBall(gamePane);
                    controller.shootBall(game , ball , gamePane);

                    int shotBalls = game.getTotalBalls() - game.getBallsLeft(); // change phase
                    controller.checkPhaseChange(shotBalls , game);

                    numberOfBallsLeft.setText("Number of balls left :" + game.getBallsLeft());
                }
                else if (keyName.equals(game.getCurrentPlayer().getPauseKey()) && !game.isPaused()) {
                    try {
                        controller.pause(game);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (keyName.equals(game.getCurrentPlayer().getPauseKey()) && game.isPaused()) {
                    controller.resume(game);
                }
                else if (keyName.equals(game.getCurrentPlayer().getFreezeKey()) && !game.isPaused()) {
                    controller.freeze(game);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
