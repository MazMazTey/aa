package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import view.RotationAnimation;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final boolean is2Player;
    private User currentPlayer;
    private int phase;
    private final CenterCircle centerCircle;
    private final ArrayList<Ball> allBalls;
    private final ArrayList<Ball> ballsOnTheCircle;
    private final HashMap<Ball , Line> ballsAndLines;
    private int ballsLeft;
    private final int totalBalls;
    private final int initBalls;

    private RotationAnimation rotationAnimation;
    private boolean gameOver;
    private boolean isPaused;
    private boolean isSlowed;
    private boolean gameReady;
    private boolean rotationReversed;
    private double speed;
    private int score;

    public Game(boolean is2Player, int totalBalls, int initBalls) {
        this.is2Player = is2Player;
        this.initBalls = initBalls;
        this.totalBalls = totalBalls;
        ballsLeft = this.totalBalls + 1;
        this.phase = 1;
        currentPlayer = AA.getLoggedInUser();
        if (is2Player) centerCircle = new CenterCircle(300);
        else centerCircle = new CenterCircle(200);
        allBalls = new ArrayList<>();
        ballsOnTheCircle = new ArrayList<>();
        ballsAndLines = new HashMap<>();
        isPaused = false;
        gameOver = false;
        isSlowed = false;
        gameReady = false;
        rotationReversed = false;
        speed = 1;
        score = 0;
    }

    public boolean isIs2Player() {
        return is2Player;
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

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public CenterCircle getCenterCircle() {
        return centerCircle;
    }

    public ArrayList<Ball> getBallsOnTheCircle() {
        return ballsOnTheCircle;
    }

    public void addBallToCircle(Ball ball) {
        ballsOnTheCircle.add(ball);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void initializeBall(Pane gamePane) {
        ballsLeft--;
        Ball ball = new Ball();
        if (ballsLeft <= 0) {
            gameOver = true;
        }
        else {
            gamePane.getChildren().add(ball);
            allBalls.add(ball);
        }
    }

    public RotationAnimation getRotationAnimation() {
        return rotationAnimation;
    }

    public void setRotationAnimation(RotationAnimation rotationAnimation) {
        this.rotationAnimation = rotationAnimation;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public int getBallsLeft() {
        return ballsLeft;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getTotalBalls() {
        return totalBalls;
    }

    public boolean isSlowed() {
        return isSlowed;
    }

    public void setSlowed(boolean slowed) {
        isSlowed = slowed;
    }

    public void addLine(Ball ball , Line line) {
        ballsAndLines.put(ball , line);
    }

    public Line getLineByBall(Ball ball) {
        return ballsAndLines.get(ball);
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }

    public int getInitBalls() {
        return initBalls;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void toggleRotationReversion() {
        rotationReversed = !rotationReversed;
    }

    public ArrayList<Ball> getAllBalls() {
        return allBalls;
    }
}
