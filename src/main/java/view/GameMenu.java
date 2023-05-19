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
        Ball ball = createBall(gamePane);
        gamePane.getChildren().add(game.getCenterCircle());
        gamePane.getChildren().add(ball);
        gamePane.getChildren().get(1).requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    private Ball createBall(Pane gamePane) {
        Ball ball = new Ball();
        ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals(game.getCurrentPlayer().getShootBallKey())) {
                    controller.shootBall(game , ball , gamePane);
                }
            }
        });
        return ball;
    }
}
