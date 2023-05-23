package view;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Ball;
import model.Game;

public class GameMenuController {
    private final GameController controller;

    public GameMenuController() {
        this.controller = new GameController();
    }

    public void shootBall(Game game, Ball ball , Pane gamePane, ProgressBar progressBar) {
        controller.shootBall(game , ball , gamePane , progressBar);
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

    public void result(Game game , VBox vBox , int score, Pane gamePane) {
        controller.result(game , score);
        vBox.setLayoutX(210);
        vBox.setLayoutY(500);
        Text text1 = new Text("Game Over!");
        text1.setFont(Font.font("Verdana" , 44));
        text1.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text1);
        Text text2 = new Text("You're Score : " + score);
        text2.setFont(Font.font("Verdana" , 20));
        text2.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(text2);
        Button button = new Button("Back To Main Menu" );
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new MainMenu().start(RegisterMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button.setAlignment(Pos.CENTER);
        vBox.getChildren().add(button);
        vBox.setSpacing(10);
        gamePane.getChildren().add(vBox);
    }

    public void checkPhaseChange(int shotBalls , Game game) {
        controller.checkPhaseChange(shotBalls , game);
    }

    public void freeze(Game game) {
        controller.freeze(game);
    }

    public boolean collide(Game game) {
        return controller.collide(game);
    }


    public void lose(Game game, Pane gamePane) {
        controller.lose(game , gamePane);
    }
}
