package model;

import javafx.scene.image.ImageView;

public class User {
    private String username;
    private String password;
    private String difficulty;
    private int highScore;
    private ImageView avatar;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        highScore = 0;
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

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }
}
