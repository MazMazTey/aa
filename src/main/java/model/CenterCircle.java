package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CenterCircle extends Circle {
    private double rotationSpeed;

    public CenterCircle() {
        super(300 , 200 , 50 , Color.BLACK);
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
