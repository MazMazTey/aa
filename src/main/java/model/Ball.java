package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import view.Phase2Animation;
import view.Phase3Animation;
import view.RotationAnimation;

public class Ball extends Circle {
    private RotationAnimation rotationAnimation;
    private Phase2Animation phase2Animation;
    private Phase3Animation phase3Animation;
    private Color defaultColor;
    private Text number;
    public Ball(int height) {
        super(300, height, 8, Color.BLACK);
        number = new Text("5"); // TODO add number to balls
        rotationAnimation = null;
        phase2Animation = null;
        phase3Animation = null;
        defaultColor = Color.BLACK;
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

    public Phase3Animation getPhase3Animation() {
        return phase3Animation;
    }

    public void setPhase3Animation(Phase3Animation phase3Animation) {
        this.phase3Animation = phase3Animation;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }
}
