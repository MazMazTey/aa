package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.Game;

public class Phase2Animation {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private final Ball ball;
    private Timeline sizeChangeAnimation;
    private final int duration;

    public Phase2Animation(Game game, Pane gamePane, Ball ball,
                           GameController controller) {
        this.game = game;
        this.controller = controller;
        this.gamePane = gamePane;
        this.ball = ball;
        duration = 1000;
    }

    public Timeline getSizeChangeAnimation() {
        return sizeChangeAnimation;
    }

    public void changeBallSize() {
        Timeline growthTimeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(ball.radiusProperty(), ball.getRadius())),
                new KeyFrame(Duration.millis(duration),
                        new KeyValue(ball.radiusProperty(), ball.getRadius() * (1 + 0.05))));
        growthTimeline.setAutoReverse(true);
        growthTimeline.setCycleCount(-1);
        growthTimeline.play();
        sizeChangeAnimation = growthTimeline;
        ball.setPhase2Animation(this);
    }

}
