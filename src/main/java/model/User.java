package model;

import javafx.scene.shape.Rectangle;

public class User {
    private String username;
    private String password;
    private String difficulty;
    private int highScore;
    private Rectangle avatar;
    private final boolean isGuest;
    private String shootBallKey;
    private String freezeKey;
    private String pauseKey;

    public User(String username, String password, boolean isGuest) {
        this.username = username;
        this.password = password;
        this.isGuest = isGuest;
        difficulty = "Easy";
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

    public boolean isGuest() {
        return isGuest;
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

    public int getFreezeTime() {
        return switch (difficulty) {
            case "Easy" -> 7000;
            case "Medium" -> 5000;
            default -> 3000;
        };
    }
}
