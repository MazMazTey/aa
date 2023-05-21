package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));
        Scene scene = new Scene(gamePane);
        gamePane.getChildren().add(game.getCenterCircle());
        gamePane.getChildren().get(0).requestFocus();
        Ball ball = game.initializeBall(gamePane);
        game.getCenterCircle().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (game.isGameOver()) {
                    System.out.println("Game Over!");
                }
                if (keyName.equals(game.getCurrentPlayer().getShootBallKey())
                        && !game.isGameOver()) {
                    Ball ball = game.initializeBall(gamePane);
                    controller.shootBall(game , ball , gamePane);

                    if (game.isGameOver()) {
                        gamePane.getChildren().removeAll();
                        System.exit(0);
                    }
                }
                else if (keyName.equals(game.getCurrentPlayer().getPauseKey()) && !game.isPaused()) {
                    game.getRotationAnimation().pauseRotate();
                    try {
                        controller.pause(game);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (keyName.equals(game.getCurrentPlayer().getPauseKey()) && game.isPaused()) {
                    game.getRotationAnimation().resumeRotate();
                    controller.resume(game);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
