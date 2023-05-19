package model;

import java.util.ArrayList;

public class Game {
    private User currentPlayer;
    private int phase;
    private final CenterCircle centerCircle;
    private final ArrayList<Ball> ballsOnTheCircle;

    public Game() {
        this.phase = 1;
        currentPlayer = AA.getLoggedInUser();
        centerCircle = new CenterCircle();
        ballsOnTheCircle = new ArrayList<>();
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

    public void addBallToCircle(Ball ball) {
        ballsOnTheCircle.add(ball);
    }
}
