package view;

import controller.GameController;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import model.Ball;
import model.Game;

public class Phase3Animation {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private final Ball ball;
    private Timeline fadeAnimation;

    public Phase3Animation(Game game, GameController controller,
                           Pane gamePane, Ball ball) {
        this.game = game;
        this.controller = controller;
        this.gamePane = gamePane;
        this.ball = ball;
    }

    public Timeline getFadeAnimation() {
        return fadeAnimation;
    }

    public void fade() {

    }
}
