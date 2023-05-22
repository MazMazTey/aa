package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

public class RotationAnimation{
    private final Game game;
    private final CenterCircle centerCircle;
    private final Ball ball;
    private final Line line;
    private Timeline timeLine;
    private Rotate rotate;
    private double periodicity;


    public RotationAnimation(Game game , Pane gamePane, CenterCircle centerCircle , Ball ball) {
        this.centerCircle = centerCircle;
        this.ball = ball;
        this.game = game;
        this.line = new Line(ball.getCenterX() , ball.getCenterY() , centerCircle.getCenterX() , centerCircle.getCenterY());
        gamePane.getChildren().add(line);
        game.setRotationAnimation(this);
        this.periodicity = 2000;
    }

    public Timeline getTimeLine() {
        return timeLine;
    }

    public void rotateBall() {
        if (game.isGameOver()) return;
        rotate = new Rotate();
        rotate.setPivotX(centerCircle.getCenterX());
        rotate.setPivotY(centerCircle.getCenterY());
        ball.translateXProperty().bind(centerCircle.translateXProperty());
        ball.translateYProperty().bind(centerCircle.translateYProperty());
        ball.getTransforms().add(rotate);
        line.translateXProperty().bind(centerCircle.translateXProperty());
        line.translateYProperty().bind(centerCircle.translateYProperty());
        line.getTransforms().add(rotate);
        timeLine = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.millis(periodicity), new KeyValue(rotate.angleProperty(), 360))
        );
        timeLine.setCycleCount(Timeline.INDEFINITE);

        ball.setRotationAnimation(this);
        if (game.isSlowed()) ball.getRotationAnimation().timeLine.setRate(0.2);
        timeLine.play();
    }

    public void pauseRotate() {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            ball1.getRotationAnimation().timeLine.pause();
        }
    }

    public void resumeRotate() {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            ball1.getRotationAnimation().timeLine.play();
        }
    }

    public void slowRotate() {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            ball1.getRotationAnimation().timeLine.setRate(0.2);
        }
    }
}
