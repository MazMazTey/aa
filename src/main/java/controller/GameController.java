package controller;

import javafx.scene.layout.Pane;
import model.Ball;
import model.Game;
import view.ShootingAnimation;


public class GameController {

    public void shootBall(Game game, Ball ball , Pane gamePane) {
        ShootingAnimation animation = new ShootingAnimation(game , gamePane , ball , game.getCenterCircle());
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

    public void checkPhaseChange(int shotBalls , Game game) {
        float v = (float) shotBalls / (float) game.getTotalBalls();
        if (v >= 0.75) game.setPhase(4);
        else if (v >= 0.5) game.setPhase(3);
        else if (v >= 0.25) game.setPhase(2);
    }

    public void freeze(Game game) {
        game.setSlowed(true);
        game.getRotationAnimation().slowRotate();
    }
}
