package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import view.RotationAnimation;

public class Ball extends Circle {
    private RotationAnimation rotationAnimation;
    public Ball() {
        super(300, 600, 12, Color.BLACK);
    }

    public RotationAnimation getRotationAnimation() {
        return rotationAnimation;
    }

    public void setRotationAnimation(RotationAnimation rotationAnimation) {
        this.rotationAnimation = rotationAnimation;
    }
}
