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
    private final Line needle;
    private Timeline timeLine;
    private Rotate rotate;
    private double periodicity;


    public RotationAnimation(Game game , Pane gamePane, CenterCircle centerCircle , Ball ball) {
        this.centerCircle = centerCircle;
        this.ball = ball;
        this.game = game;
        this.needle = new Line(ball.getCenterX() , ball.getCenterY() , centerCircle.getCenterX() , centerCircle.getCenterY());
        gamePane.getChildren().add(needle);
        game.setRotationAnimation(this);
        game.addLine(ball , needle);
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
        needle.translateXProperty().bind(centerCircle.translateXProperty());
        needle.translateYProperty().bind(centerCircle.translateYProperty());
        needle.getTransforms().add(rotate);
        timeLine = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                new KeyFrame(Duration.millis(periodicity), new KeyValue(rotate.angleProperty(), 360))
        );
        timeLine.setCycleCount(Timeline.INDEFINITE);

        ball.setRotationAnimation(this);
        switch (game.getCurrentPlayer().getDifficulty()) {
            case "Easy" -> ball.getRotationAnimation().timeLine.setRate(1);
            case "Medium" -> ball.getRotationAnimation().timeLine.setRate(2.1);
            case "Hard" -> ball.getRotationAnimation().timeLine.setRate(3.2);
        }
        if (game.isSlowed()) ball.getRotationAnimation().timeLine.setRate(0.2);
        timeLine.play();
    }

    public void slowRotate() {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            if (ball1.getRotationAnimation() != null)
                ball1.getRotationAnimation().timeLine.setRate(0.2);
        }
    }

    public void speedRotate() {
        if (game.isGameOver()) return;
        double speed = 1;
        switch (game.getCurrentPlayer().getDifficulty()) {
            case "Easy" -> speed = 1;
            case "Medium" -> speed = 1.1;
            case "Hard" -> speed = 1.2;
        }
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            if (ball1.getRotationAnimation() != null)
                ball1.getRotationAnimation().timeLine.setRate(speed);
        }
    }
}
