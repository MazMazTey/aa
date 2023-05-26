package model;

import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class User implements Comparable<User>{
    private String username;
    private String password;
    private String difficulty;
    private int highScore;
    private URL avatarPath;
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
        switch (difficulty) {
            case "Easy" -> highScore += amount;
            case "Medium" -> highScore += 1.2 * amount;
            case "Hard" -> highScore += 1.4 * amount;
        }
    }

    public URL getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(URL avatarPath) {
        this.avatarPath = avatarPath;
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

    @Override
    public int compareTo(@NotNull User o) {
        return this.highScore - o.highScore;
    }
}
