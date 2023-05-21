package view;

import controller.GameController;
import javafx.scene.layout.Pane;
import model.Ball;
import model.Game;

public class GameMenuController {
    private final GameController controller;

    public GameMenuController() {
        this.controller = new GameController();
    }

    public void shootBall(Game game, Ball ball , Pane gamePane) {
        controller.shootBall(game , ball , gamePane);
    }
    public void pause(Game game) throws Exception {
        controller.pause(game);
    }

    public void resume(Game game) {
        controller.resume(game);
    }
}
