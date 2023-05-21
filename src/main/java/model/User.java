package model;

import javafx.scene.shape.Rectangle;

public class User {
    private String username;
    private String password;
    private String difficulty;
    private int highScore;
    private Rectangle avatar;
    private String shootBallKey;
    private String freezeKey;
    private String pauseKey;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        highScore = 0;
        shootBallKey = "Space";
        freezeKey = "Tab";
        pauseKey = "P";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getHighScore() {
        return highScore;
    }

    public void addToHighScore(int amount) {
        highScore += amount;
    }

    public Rectangle getAvatar() {
        return avatar;
    }

    public void setAvatar(Rectangle avatar) {
        this.avatar = avatar;
    }

    public String getShootBallKey() {
        return shootBallKey;
    }

    public void setShootBallKey(String shootBallKey) {
        this.shootBallKey = shootBallKey;
    }

    public String getFreezeKey() {
        return freezeKey;
    }

    public void setFreezeKey(String freezeKey) {
        this.freezeKey = freezeKey;
    }

    public String getPauseKey() {
        return pauseKey;
    }

    public void setPauseKey(String pauseKey) {
        this.pauseKey = pauseKey;
    }
}
