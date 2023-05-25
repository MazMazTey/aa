package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AA;
import model.Ball;
import model.Game;

import java.util.Timer;
import java.util.TimerTask;

public class GameMenu extends Application {
    private final Game game;
    private final GameMenuController controller;
    public GameMenu(Game game) {
        this.game = game;
        this.controller = new GameMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (!AA.getLoggedInUser().isGuest()) game.setCurrentPlayer(AA.getLoggedInUser());
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));
        Scene scene = new Scene(gamePane);
        gamePane.getChildren().add(game.getCenterCircle());
        gamePane.getChildren().get(0).requestFocus();
        //Ball ball = game.initializeBall(gamePane);

        HBox hBox = new HBox();
        Text freezeCoolDown = new Text();
        Text numberOfBallsLeft = new Text("Number of balls left : " + game.getBallsLeft());
        ProgressBar progressBar = new ProgressBar(1);
        Text showScore = new Text("Score : " + game.getScore());
        controller.createHbox(hBox , freezeCoolDown , numberOfBallsLeft , progressBar , showScore);
        gamePane.getChildren().add(hBox);
        Text showPhase = new Text("Phase : " + game.getPhase());
        showPhase.setLayoutX(540);
        showPhase.setLayoutY(10);
        gamePane.getChildren().add(showPhase);
        Timer timer = new Timer(); // check collision during game
        TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (game.isGameOver()) {
                        timer.cancel();
                        return;
                    }
                    if (controller.collide(game)) {
                        controller.lose(game, gamePane);
                        System.out.println("You Lost!");
                        timer.cancel();
                    }
                }
            };
        timer.scheduleAtFixedRate(task , 0 , 10);
        initializeMap(game.getInitBalls() , game , gamePane);
        game.getCenterCircle().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (game.isGameOver()) {
                    System.out.println("Game Over!");
                    VBox vBox = new VBox();
                    controller.result(game, vBox , game.getScore() , gamePane);
                }
                if (keyName.equals(game.getCurrentPlayer().getShootBallKey())
                        && !game.isGameOver() && !game.isPaused() && game.isGameReady()) {
                    Ball ball = game.initializeBall(gamePane);
                    controller.shootBall(game , ball , gamePane , progressBar , showScore);

                    int shotBalls = game.getTotalBalls() - game.getBallsLeft(); // change phase
                    controller.checkPhaseChange(shotBalls , game);

                    numberOfBallsLeft.setText("Number of balls left : " + game.getBallsLeft());
                    showPhase.setText("Phase : " + game.getPhase());
                }
                else if (keyName.equals(game.getCurrentPlayer().getPauseKey()) &&
                        !game.isPaused() && game.isGameReady()) {
                    try {
                        controller.pause(stage , scene, game);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (keyName.equals(game.getCurrentPlayer().getFreezeKey()) &&
                        !game.isPaused() && game.isGameReady() && progressBar.getProgress() >= 0.99) {
                    progressBar.setProgress(0);
                    controller.freeze(game);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private void initializeMap(int initBalls , Game game ,
                               Pane gamePane) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Ball ball = new Ball();
                new StartGameAnimation(game , gamePane , ball , game.getCenterCircle()).play();
            }
        }) , new KeyFrame(Duration.millis(2000 / initBalls)));
        timeline.setCycleCount(initBalls);
        timeline.play();
        game.setGameReady(true);
    }
}
