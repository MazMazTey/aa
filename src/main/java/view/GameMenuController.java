package view;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Ball;
import model.Game;

public class GameMenuController {
    private final GameController controller;

    public GameMenuController() {
        this.controller = new GameController();
    }

    public void shootBall(Game game, Ball ball , Pane gamePane) {
        controller.shootBall(game , ball , gamePane);
    }
    public void pause(Game game) throws Exception {
        controller.pause(game);
    }

    public void resume(Game game) {
        controller.resume(game);
    }

    public void createHbox(HBox hBox, Text freezeCoolDown
            ,Text numberOfBallsLeft, ProgressBar progressBar) {
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(numberOfBallsLeft);
        hBox.getChildren().add(freezeCoolDown);
        hBox.setSpacing(30);
        hBox.getChildren().add(progressBar);
    }
}
