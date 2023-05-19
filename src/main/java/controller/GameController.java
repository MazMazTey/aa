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
}
