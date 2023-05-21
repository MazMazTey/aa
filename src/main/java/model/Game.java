package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import view.RotationAnimation;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private User currentPlayer;
    private int phase;
    private final CenterCircle centerCircle;
    private final ArrayList<Ball> ballsOnTheCircle;
    private final HashMap<Ball , Line> ballsAndLines;
    private int totalBalls;

    private RotationAnimation rotationAnimation;
    private boolean gameOver;

    public Game() {
        this.phase = 1;
        currentPlayer = AA.getLoggedInUser();
        centerCircle = new CenterCircle();
        ballsOnTheCircle = new ArrayList<>();
        ballsAndLines = new HashMap<>();
        gameOver = false;
        totalBalls = 10;
    }

    public User getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(User currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getPhase() {
        return phase;
    }

    public void nextPhase() {
        phase++;
    }

    public CenterCircle getCenterCircle() {
        return centerCircle;
    }

    public ArrayList<Ball> getBallsOnTheCircle() {
        return ballsOnTheCircle;
    }

    public void addBallToCircle(Ball ball , Line line) {
        ballsOnTheCircle.add(ball);
        ballsAndLines.put(ball , line);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Ball initializeBall(Pane gamePane) {
        if (totalBalls <= 0) {
            gameOver = true;
            return null;
        }
        Ball ball = new Ball();
        gamePane.getChildren().add(ball);
        totalBalls--;
        return ball;
    }

    public RotationAnimation getRotationAnimation() {
        return rotationAnimation;
    }

    public void setRotationAnimation(RotationAnimation rotationAnimation) {
        this.rotationAnimation = rotationAnimation;
    }
}
