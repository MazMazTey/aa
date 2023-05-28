package view;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.CenterCircle;
import model.Game;

public class ShootingAnimation extends Transition {
    private final Game game;
    private final GameController controller;
    private final Pane gamePane;
    private final Ball ball;
    private final CenterCircle centerCircle;
    private boolean isStopped;
    private final ProgressBar freezeCoolDown;
    private final Text showScore;
    private String keyPressed;
    private int heightLimitUp;
    private int heightLimitDown;

    public ShootingAnimation(Game game, Pane gamePane, Ball ball, CenterCircle centerCircle,
                             ProgressBar progressBar, GameController gameController , Text showScore) {
        this.game = game;
        this.controller = gameController;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        this.showScore = showScore;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
        isStopped = false;
        freezeCoolDown = progressBar;
        heightLimitDown = 340;
    }

    public ShootingAnimation(Game game, Pane gamePane, Ball ball, CenterCircle centerCircle,
                             ProgressBar progressBar, GameController gameController , Text showScore , String keyPressed) {
        this.game = game;
        this.controller = gameController;
        this.gamePane = gamePane;
        this.ball = ball;
        this.centerCircle = centerCircle;
        this.showScore = showScore;
        this.keyPressed = keyPressed;
        this.setCycleDuration(Duration.millis(800));
        this.setCycleCount(1);
        isStopped = false;
        freezeCoolDown = progressBar;
        heightLimitDown = 440;
        heightLimitUp = 240;
    }

    @Override
    protected void interpolate(double v) {
        double y;
        if (!game.is2Player() || (game.is2Player() && keyPressed.equals(game.getCurrentPlayer().getShootBallKey()))) {
            y = ball.getCenterY() - 20;
            if ((y <= heightLimitDown && game.getPhase() < 4) || (
                    game.getPhase() == 4 && Math.sqrt(
                            Math.pow(centerCircle.getCenterX() - ball.getCenterX() , 2) +
                                    Math.pow(centerCircle.getCenterY() - ball.getCenterY() , 2)) <= 160)) {
                this.stop();
                isStopped = true;
                game.addBallToCircle(ball);
                if (!game.is2Player()) {
                    game.addScore(5);
                    showScore.setText("Score : " + game.getScore());
                }
                if (checkGameSlowed()) return;
            }
        }
        else {
            y = ball.getCenterY() + 20;
            if ((y <= 440 && game.getPhase() < 4) || (
                    game.getPhase() == 4 && Math.sqrt(
                            Math.pow(centerCircle.getCenterX() - ball.getCenterX() , 2) +
                                    Math.pow(centerCircle.getCenterY() - ball.getCenterY() , 2)) <= 160)) {
                this.stop();
                isStopped = true;
                game.addBallToCircle(ball);
                if (checkGameSlowed()) return;
            }
        }
        if (!isStopped) ball.setCenterY(y);
    }

    private boolean checkGameSlowed() {
        if (game.isSlowed()) {
            ball.setFill(Color.BLUE);
        }
        if (game.isGameOver() && !controller.collide(game)) {
            gamePane.getChildren().add(new Line(centerCircle.getCenterX() ,
                    centerCircle.getCenterY() , ball.getCenterX() , ball.getCenterY()));
            controller.win(game, gamePane);
            return true;
        }
        freezeCoolDown.setProgress(freezeCoolDown.getProgress() + 0.25);
        controller.changePhase(game.getPhase() , game , gamePane , ball);
        return false;
    }
}
