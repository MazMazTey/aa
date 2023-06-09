package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AA;
import model.Ball;
import model.Game;
import view.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameController {
    private boolean started = false;

    public void shootBall(Game game, Ball ball, Pane gamePane,
                          ProgressBar progressBar, Text showScore) {
        ShootingAnimation animation = new ShootingAnimation(game, gamePane,
                ball, game.getCenterCircle(), progressBar, this, showScore);
        animation.play();
    }

    public void shootBall(Game game, Ball ball, Pane gamePane,
                          ProgressBar progressBar, Text showScore , String keyName) {
        ShootingAnimation animation = new ShootingAnimation(game, gamePane,
                ball, game.getCenterCircle(), progressBar, this, showScore , keyName);
        animation.play();
    }

    public void pause(Stage stage, Scene scene, Game game) throws Exception {
        int totalBalls = game.getTotalBalls();
        pauseAllAnimations(game);
        game.setPaused(true);
        BorderPane borderPane = FXMLLoader.load(GameController.class.
                getResource("/FXML/PauseMenu.fxml"));
        Scene newScene = new Scene(borderPane);
        VBox vBox = new VBox();
        borderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        Label label = new Label();
        label.setText("Pause Menu");
        label.setLabelFor(borderPane);
        vBox.getChildren().add(label);
        vBox.getChildren().add(new Text("Shoot Key : " +
                game.getCurrentPlayer().getShootBallKey()));
        vBox.getChildren().add(new Text("Freeze Key : " +
                game.getCurrentPlayer().getFreezeKey()));
        vBox.getChildren().add(new Text("Pause Key : " +
                game.getCurrentPlayer().getPauseKey()));
        vBox.getChildren().add(new Text("Move Left Key : " +
                game.getCurrentPlayer().getMoveLeftKey()));
        vBox.getChildren().add(new Text("Move Right Key : " +
                game.getCurrentPlayer().getMoveRightKey()));

        Button backButton = new Button("Resume Game");
        Button exitButton = new Button("Exit Game");
        Button restartButton = new Button("Restart Game");
        Button saveButton = new Button("Save Game");
        Button muteButton = new Button("Mute Song");
        Button changeSongButton = new Button("Change Song");
        backButton.setOnAction(actionEvent -> {
            stage.setScene(scene);
            game.setPaused(false);
            resumeAllAnimations(game);
        });
        restartButton.setOnAction(actionEvent -> {
            try {
                stopAllAnimations(game);
                game.setGameOver(true);
                new GameMenu(new Game(game.is2Player(), totalBalls,
                        game.getInitBalls())).start(RegisterMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        exitButton.setOnAction(actionEvent -> {
            try {
                stopAllAnimations(game);
                game.setGameOver(true);
                new MainMenu().start(RegisterMenu.stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        saveButton.setOnAction(actionEvent -> {
            // TODO implement save game
        });
        muteButton.setOnAction(actionEvent -> Settings.muteSong(muteButton));
        changeSongButton.setOnAction(actionEvent -> {
            try {
                Stage newStage = new Stage();
                new SelectSong().start(newStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        vBox.getChildren().add(backButton);
        vBox.getChildren().add(restartButton);
        vBox.getChildren().add(muteButton);
        vBox.getChildren().add(changeSongButton);
        vBox.getChildren().add(saveButton);
        vBox.getChildren().add(exitButton);
        vBox.setSpacing(15);


        stage.setScene(newScene);
        stage.show();
    }

    public void randomReverse(Game game) {
        int delay = new Random().nextInt(4000, 6000);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, actionEvent -> {
            game.toggleRotationReversion();
            if (!game.isSlowed()) {
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (ball1.getRotationAnimation() != null) {
                        ball1.getRotationAnimation().getTimeLine().setRate(game.getSpeed());
                    }
                }
            } else {
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (ball1.getRotationAnimation() != null) {
                        ball1.getRotationAnimation().getTimeLine().setRate(-game.getSpeed());
                    }
                }
            }
        }), new KeyFrame(Duration.millis(delay)));
        timeline.setCycleCount(1);
        timeline.setOnFinished(actionEvent -> {
            game.setSpeed(-game.getSpeed());
            randomReverse(game);
        });
        timeline.play();
    }

    public void result(Game game, int score) {
        game.getCurrentPlayer().addToHighScore(score);
        AA.leaderBoard();
    }

    public void checkPhaseChange(int shotBalls, Game game) {
        float v = (float) shotBalls / (float) game.getTotalBalls();
        if (v >= 0.75) game.setPhase(4);
        else if (v >= 0.5) game.setPhase(3);
        else if (v >= 0.25) game.setPhase(2);
    }

    public void freeze(Game game) {
        game.setSpeed(0.2);
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

    public void changePhase(int phase, Game game, Pane gamePane, Ball ball) {
        switch (phase) {
            case 1 -> new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
            case 2 -> {
                if (!started) {
                    randomReverse(game);
                    started = true;
                }
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (!game.is2Player()) {
                        ball1.setDefaultColor(Color.GREENYELLOW);
                        if (!game.isSlowed()) ball1.setFill(Color.GREENYELLOW);
                    }
                    if (ball1.getPhase2Animation() == null) {
                        Phase2Animation phase2Animation = new Phase2Animation(
                                game, gamePane, ball1, this);
                        phase2Animation.changeBallSize();
                    }
                }
            }
            case 3 -> {
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (!game.is2Player()) {
                        ball1.setDefaultColor(Color.CRIMSON);
                        if (!game.isSlowed()) ball1.setFill(Color.CRIMSON);
                    }
                    if (ball1.getPhase2Animation() == null) {
                        Phase2Animation phase2Animation = new Phase2Animation(
                                game, gamePane, ball1, this);
                        phase2Animation.changeBallSize();
                    }
                    if (ball1.getPhase3Animation() == null)
                        new Phase3Animation(game, this, gamePane, ball1).fade();
                }
            }
            case 4 -> {
                new RotationAnimation(game, gamePane, game.getCenterCircle(), ball).rotateBall();
                for (Ball ball1 : game.getBallsOnTheCircle()) {
                    if (!game.is2Player()) {
                        ball1.setDefaultColor(Color.DARKGOLDENROD);
                        if (!game.isSlowed()) ball1.setFill(Color.DARKGOLDENROD);
                    }
                    if (ball1.getPhase2Animation() == null) {
                        Phase2Animation phase2Animation = new Phase2Animation(
                                game, gamePane, ball1, this);
                        phase2Animation.changeBallSize();
                    }
                    if (ball1.getPhase3Animation() == null)
                        new Phase3Animation(game, this, gamePane, ball1).fade();
                }
            }
        }
    }

    public void lose(Game game, Pane gamePane) {
        game.setGameOver(true);
        gamePane.setBackground(new Background(new BackgroundFill(Color.RED
                , CornerRadii.EMPTY, Insets.EMPTY)));
        stopAllAnimations(game);
    }

    public void win(Game game, Pane gamePane) {
        game.setGameOver(true);
        gamePane.setBackground(new Background(new BackgroundFill(Color.GREEN
                , CornerRadii.EMPTY, Insets.EMPTY)));
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

    private void pauseAllAnimations(Game game) {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            if (ball1.getRotationAnimation() != null)
                ball1.getRotationAnimation().getTimeLine().pause();
            if (ball1.getPhase2Animation() != null)
                ball1.getPhase2Animation().getSizeChangeAnimation().pause();
            if (ball1.getPhase3Animation() != null)
                ball1.getPhase3Animation().getFadeAnimation().pause();
        }
    }

    private void resumeAllAnimations(Game game) {
        for (Ball ball1 : game.getBallsOnTheCircle()) {
            if (ball1.getRotationAnimation() != null)
                ball1.getRotationAnimation().getTimeLine().play();
            if (ball1.getPhase2Animation() != null)
                ball1.getPhase2Animation().getSizeChangeAnimation().play();
            if (ball1.getPhase3Animation() != null)
                ball1.getPhase3Animation().getFadeAnimation().play();
        }
    }
}
