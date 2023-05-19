package view;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

public class ShootingAnimation extends Transition {
    private final Game game;
    private final Pane gamePane;
    private Ball ball;
    private CenterCircle centerCircle;

    public ShootingAnimation(Game game, Pane gamePane, Ball ball, CenterCircle centerCircle) {
        this.game = game;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        if (game.getPhase() < 4) {
            double y = ball.getCenterY() - 20;
            if (y <= 300) {
                this.stop();
            }
            ball.setCenterY(y);
        }
    }
}
