package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.RotationAnimation;

public class Ball extends Circle {
    private RotationAnimation rotationAnimation;
    private Text number;
    public Ball() {
        super(300, 600, 12, Color.BLACK);
        number = new Text("5"); // TODO add number to balls
    }

    public RotationAnimation getRotationAnimation() {
        return rotationAnimation;
    }

    public void setRotationAnimation(RotationAnimation rotationAnimation) {
        this.rotationAnimation = rotationAnimation;
    }
}
