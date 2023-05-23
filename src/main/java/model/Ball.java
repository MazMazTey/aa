package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.Phase2Animation;
import view.RotationAnimation;

public class Ball extends Circle {
    private RotationAnimation rotationAnimation;
    private Phase2Animation phase2Animation;
    private Text number;
    public Ball() {
        super(300, 600, 10, Color.BLACK);
        number = new Text("5"); // TODO add number to balls
    }

    public RotationAnimation getRotationAnimation() {
        return rotationAnimation;
    }

    public void setRotationAnimation(RotationAnimation rotationAnimation) {
        this.rotationAnimation = rotationAnimation;
    }

    public Phase2Animation getPhase2Animation() {
        return phase2Animation;
    }

    public void setPhase2Animation(Phase2Animation phase2Animation) {
        this.phase2Animation = phase2Animation;
    }
}
