package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.Game;

import java.util.Random;

public class Phase2Animation {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private final Ball ball;
    private Timeline sizeChangeAnimation;
    private int duration;
    private double direction;

    public Phase2Animation(Game game, Pane gamePane, Ball ball ,
                           GameController controller) {
        this.game = game;
        this.controller = controller;
        this.gamePane = gamePane;
        this.ball = ball;
        duration = 1000;
        direction = game.getBallsOnTheCircle().get(0).
                getRotationAnimation().getTimeLine().getRate();
    }

    public Timeline getSizeChangeAnimation() {
        return sizeChangeAnimation;
    }

    public void randomReverse() {
        int delay = new Random().nextInt(4000, 6000);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (ball1.getRotationAnimation() != null) {
                        ball1.getRotationAnimation().getTimeLine().setRate(-direction);
                    }
                }
            }
        }) , new KeyFrame(Duration.millis(delay)));
        timeline.setCycleCount(1);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                direction = - direction;
                randomReverse();
            }
        });
        timeline.play();
    }

    public void changeBallSize() {
        Timeline growthTimeline = new Timeline(
                new KeyFrame(Duration.ZERO ,
                        new KeyValue(ball.radiusProperty(), ball.getRadius())) ,
                new KeyFrame(Duration.millis(duration) ,
                        new KeyValue(ball.radiusProperty() , ball.getRadius() * (1 + 0.05))));
        growthTimeline.setAutoReverse(true);
        growthTimeline.setCycleCount(-1);
        growthTimeline.play();
        sizeChangeAnimation = growthTimeline;
        ball.setPhase2Animation(this);
    }

}
