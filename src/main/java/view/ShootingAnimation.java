package view;

import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

import java.util.ArrayList;

public class ShootingAnimation extends Transition {
    private final Game game;
    private final Pane gamePane;
    private Ball ball;
    private CenterCircle centerCircle;
    private boolean isStopped;

    public ShootingAnimation(Game game, Pane gamePane, Ball ball, CenterCircle centerCircle) {
        this.game = game;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
        isStopped = false;
    }

    @Override
    protected void interpolate(double v) {
        if (game.getPhase() < 4) {
            double y = ball.getCenterY() - 20;
            if (y <= 300) {
                this.stop();
                isStopped = true;
                game.addBallToCircle(ball);
                if (collide(game)) {
                    System.out.println("You Lost!");
                    game.setGameOver(true);
                    for (Ball ball1 : game.getBallsOnTheCircle()) {
                        if (ball1.getRotationAnimation() != null)
                            ball1.getRotationAnimation().getTimeLine().stop();
                    }
                    gamePane.setBackground(new Background(new BackgroundFill(Color.RED
                            , CornerRadii.EMPTY , Insets.EMPTY)));
                    return;
                }
                else if (game.isGameOver()) {
                    for (Ball ball1 : game.getBallsOnTheCircle()) {
                        if (ball1.getRotationAnimation() != null)
                            ball1.getRotationAnimation().getTimeLine().stop();
                    }
                    gamePane.setBackground(new Background(new BackgroundFill(Color.GREEN
                            , CornerRadii.EMPTY , Insets.EMPTY)));
                }
                game.addScore(5);
                new RotationAnimation(game , gamePane , game.getCenterCircle(), ball).rotateBall();
            }
            if (!isStopped) ball.setCenterY(y);
        }
    }

    private boolean collide(Game game) {
        ArrayList<Ball> balls = game.getBallsOnTheCircle();
        for (int i = balls.size() - 1 ; i >= 0 ; i--) {
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (balls.get(i).getBoundsInParent()
                        .intersects(balls.get(j).getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }
}
