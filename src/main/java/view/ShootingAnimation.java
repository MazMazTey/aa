package view;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

public class ShootingAnimation extends Transition {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private Ball ball;
    private CenterCircle centerCircle;
    private boolean isStopped;
    private ProgressBar freezeCoolDown;

    public ShootingAnimation(Game game, Pane gamePane, Ball ball, CenterCircle centerCircle, ProgressBar progressBar, GameController gameController) {
        this.game = game;
        this.controller = gameController;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
        isStopped = false;
        freezeCoolDown = progressBar;
    }

    @Override
    protected void interpolate(double v) {
        if (game.getPhase() <= 4) {
            double y = ball.getCenterY() - 20;
            if (y <= 300) {
                this.stop();
                isStopped = true;
                game.addBallToCircle(ball);
                if (game.isSlowed()) {
                    ball.setFill(Color.BLUE);
                }
                if (game.isGameOver() && !controller.collide(game)) {
                    gamePane.getChildren().add(new Line(centerCircle.getCenterX() ,
                            centerCircle.getCenterY() , ball.getCenterX() , ball.getCenterY()));
                    controller.win(game, gamePane);
                    game.addScore(5);
                    return;
                }
                game.addScore(5);
                freezeCoolDown.setProgress(freezeCoolDown.getProgress() + 0.25);
                controller.changePhase(game.getPhase() , game , gamePane , ball);
            }
            if (!isStopped) ball.setCenterY(y);
        }
    }
}
