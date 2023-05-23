package view;

import controller.GameController;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.Game;

public class Phase3Animation {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private final Ball ball;
    private FadeTransition fadeAnimation;

    public Phase3Animation(Game game, GameController controller,
                           Pane gamePane, Ball ball) {
        this.game = game;
        this.controller = controller;
        this.gamePane = gamePane;
        this.ball = ball;
    }

    public FadeTransition getFadeAnimation() {
        return fadeAnimation;
    }

    public void fade() {
        FadeTransition fadeTransitionBall =
                new FadeTransition(Duration.millis(1000) , ball);
        fadeTransitionBall.setToValue(0);
        fadeTransitionBall.setAutoReverse(true);
        fadeTransitionBall.setCycleCount(-1);
        fadeTransitionBall.play();
        ball.setPhase3Animation(this);
        fadeAnimation = fadeTransitionBall;

        FadeTransition fadeTransitionLine =
                new FadeTransition(Duration.millis(1000) , game.getLineByBall(ball));
        fadeTransitionLine.setToValue(0);
        fadeTransitionLine.setAutoReverse(true);
        fadeTransitionLine.setCycleCount(-1);
        fadeTransitionLine.play();
    }
}
