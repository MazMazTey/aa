package view;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Ball;
import model.Game;

public class GameMenuController {
    private final GameController controller;

    public GameMenuController() {
        this.controller = new GameController();
    }

    public void shootBall(Game game, Ball ball , Pane gamePane, ProgressBar progressBar , Text showScore) {
        controller.shootBall(game , ball , gamePane , progressBar , showScore);
    }

    public void shootBall(Game game, Ball ball , Pane gamePane, ProgressBar progressBar , Text showScore , String keyName) {
        controller.shootBall(game , ball , gamePane , progressBar , showScore , keyName);
    }
    public void pause(Stage stage , Scene scene, Game game) throws Exception {
        controller.pause(stage, scene, game);
    }

    public void createHbox(HBox hBox, Text freezeCoolDown
            , Text numberOfBallsLeft, ProgressBar progressBar, Text score) {
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(numberOfBallsLeft);
        hBox.getChildren().add(score);
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
        HBox hBox = new HBox();

        Button backButton = new Button("Back To Main Menu" );
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new MainMenu().start(RegisterMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backButton.setAlignment(Pos.CENTER);
        hBox.getChildren().add(backButton);
        Button leaderBoardButton = new Button("Leader Board");
        leaderBoardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new LeaderBoard().start(RegisterMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        hBox.getChildren().add(leaderBoardButton);
        hBox.setSpacing(5);
        vBox.getChildren().add(hBox);
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

    public void moveRight(Ball ball) {
        if (ball.getCenterX() <= 440)
            ball.setCenterX(ball.getCenterX() + 15);
    }

    public void moveLeft(Ball ball) {
        if (ball.getCenterX() >= 160)
            ball.setCenterX(ball.getCenterX() - 15);
    }
}
