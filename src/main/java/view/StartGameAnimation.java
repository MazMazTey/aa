package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

public class StartGameAnimation extends Transition {
    private int initBalls;
    private final Game game;
    private final Pane gamePane;
    private final Ball ball;
    private final CenterCircle centerCircle;
    private boolean isStopped;

    public StartGameAnimation(Game game,
                              Pane gamePane, Ball ball,
                              CenterCircle centerCircle) {
        this.game = game;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        isStopped = false;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
        gamePane.getChildren().add(ball);
    }

    @Override
    protected void interpolate(double v) {
        double y = ball.getCenterY() - 20;
        if (y <= 340) {
            this.stop();
            isStopped = true;
            game.addBallToCircle(ball);
            new RotationAnimation(game , gamePane , centerCircle , ball).rotateBall();
        }
        if (!isStopped) ball.setCenterY(y);
    }
}
