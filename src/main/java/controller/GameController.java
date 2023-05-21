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
        game.setPaused(true);
        //new PauseMenu().start(RegisterMenu.stage);
    }

    public void resume(Game game) {
        game.setPaused(false);
    }
    private double distance(Ball ball1 , Ball ball2) {
        ball1.getBoundsInParent().intersects(ball2.getBoundsInParent());
        return 0;
    }
}
