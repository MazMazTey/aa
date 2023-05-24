package controller;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Ball;
import model.Game;
import view.Phase2Animation;
import view.Phase3Animation;
import view.RotationAnimation;
import view.ShootingAnimation;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameController {

    public void shootBall(Game game, Ball ball, Pane gamePane, ProgressBar progressBar, Text showScore) {
        ShootingAnimation animation = new ShootingAnimation(game, gamePane,
                ball, game.getCenterCircle(), progressBar, this , showScore);
        animation.play();
    }

    public void pause(Game game) throws Exception {
        game.getRotationAnimation().pauseRotate();
        game.setPaused(true);
        //new PauseMenu().start(RegisterMenu.stage); // TODO make access to pause menu
    }

    public void resume(Game game) {
        game.getRotationAnimation().resumeRotate();
        game.setPaused(false);
    }

    public void result(Game game, int score) {
        game.getCurrentPlayer().addToHighScore(score);
    }

    public void checkPhaseChange(int shotBalls, Game game) {
        float v = (float) shotBalls / (float) game.getTotalBalls();
        if (v >= 0.75) game.setPhase(4);
        else if (v >= 0.5) game.setPhase(3);
        else if (v >= 0.25) game.setPhase(2);
    }

    public void freeze(Game game) {
        game.setSlowed(true);
        game.getRotationAnimation().slowRotate();
        for (Ball ball : game.getBallsOnTheCircle()) {
            ball.setFill(Color.BLUE);
        }
        game.getCenterCircle().setFill(Color.BLUE);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                game.setSlowed(false);
                game.getRotationAnimation().speedRotate();
                for (Ball ball : game.getBallsOnTheCircle()) {
                    ball.setFill(ball.getDefaultColor());
                }
                game.getCenterCircle().setFill(Color.BLACK);
            }
        }, game.getCurrentPlayer().getFreezeTime());
    }

    public boolean collide(Game game) {
        ArrayList<Ball> balls = game.getBallsOnTheCircle();
        for (int i = balls.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (balls.get(i).getBoundsInParent()
                        .intersects(balls.get(j).getBoundsInParent())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changePhase(int phase , Game game , Pane gamePane , Ball ball) {
        switch (phase) {
            case 1:
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                break;
            case 2:
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    ball1.setDefaultColor(Color.GREENYELLOW);
                    if (!game.isSlowed()) ball1.setFill(Color.GREENYELLOW);
                    if (ball1.getPhase2Animation() == null) {
                        Phase2Animation phase2Animation = new Phase2Animation(game, gamePane, ball1, this);
                        phase2Animation.randomReverse();
                        phase2Animation.changeBallSize();
                    }
                }
                break;
            case 3 , 4:
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    ball1.setDefaultColor(Color.CRIMSON);
                    if (!game.isSlowed()) ball1.setFill(Color.CRIMSON);
                    if (ball1.getPhase2Animation() == null) {
                        Phase2Animation phase2Animation = new Phase2Animation(game, gamePane, ball1, this);
                        phase2Animation.randomReverse();
                        phase2Animation.changeBallSize();
                    }
                    if (ball1.getPhase3Animation() == null)
                        new Phase3Animation(game , this , gamePane , ball1).fade();
                }
                break;
//                    case 4:
//                        break;
        }
    }

    public void lose(Game game , Pane gamePane) {
        game.setGameOver(true);
        gamePane.setBackground(new Background(new BackgroundFill(Color.RED
                , CornerRadii.EMPTY , Insets.EMPTY)));
        stopAllAnimations(game);
    }
    public void win(Game game , Pane gamePane) {
        game.setGameOver(true);
        gamePane.setBackground(new Background(new BackgroundFill(Color.GREEN
                , CornerRadii.EMPTY , Insets.EMPTY)));
        stopAllAnimations(game);
    }

    private void stopAllAnimations(Game game) {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            if (ball1.getRotationAnimation() != null)
                ball1.getRotationAnimation().getTimeLine().stop();
            if (ball1.getPhase2Animation() != null)
                ball1.getPhase2Animation().getSizeChangeAnimation().stop();
            if (ball1.getPhase3Animation() != null)
                ball1.getPhase3Animation().getFadeAnimation().stop();
        }
    }
}
