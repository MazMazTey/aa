package view;

import controller.GameController;
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
    private final GameController controller;
    public GameMenu(Game game) {
        this.game = game;
        this.controller = new GameController();
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
                if (keyName.equals(game.getCurrentPlayer().getShootBallKey())) {
                    Ball ball = game.initializeBall(gamePane);
                    controller.shootBall(game , ball , gamePane);
                    if (game.isGameOver()) {
                        gamePane.getChildren().removeAll();
                        System.exit(0);
                    }
                }
                else if (keyName.equals(game.getCurrentPlayer().getFreezeKey())) {
                    game.getRotationAnimation().pauseRotate();
                }
                else if (keyName.equals("1")) {
                    game.getRotationAnimation().resumeRotate();
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
