package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CenterCircle extends Circle {
    private double rotationSpeed;

    public CenterCircle(int height) {
        super(300 , height , 50 , Color.BLACK);
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
