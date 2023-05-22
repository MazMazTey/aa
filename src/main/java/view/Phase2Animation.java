package view;

import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.Ball;
import model.Game;

public class Phase2Animation {
    private final Game game;
    private final Pane gamePane;
    private final Ball ball;
//    private final Line needle;
    private SequentialTransition sizeChangeAnimation;
    private int duration;

    public Phase2Animation(Game game, Pane gamePane, Ball ball) {
        this.game = game;
        this.gamePane = gamePane;
        this.ball = ball;
//        this.needle = new Line(ball.getCenterX() , ball.getCenterY() ,
//                game.getCenterCircle().getCenterX() ,
//                game.getCenterCircle().getCenterY());
//        gamePane.getChildren().add(needle);
        duration = 1000;
    }

    public void reverse() {

    }

    public void changeBallSize() {
        Timeline growthTimeline = new Timeline(
                new KeyFrame(Duration.ZERO ,
                        new KeyValue(ball.radiusProperty(), ball.getRadius())) ,
                new KeyFrame(Duration.millis(duration) ,
                        new KeyValue(ball.radiusProperty() , ball.getRadius() * (2 + 0.05))));
        Timeline shrinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO ,
                        new KeyValue(ball.radiusProperty(), ball.getRadius() * (2 + 0.05))) ,
                new KeyFrame(Duration.millis(duration) ,
                        new KeyValue(ball.radiusProperty() , ball.getRadius())));
        SequentialTransition sequentialTransition = new SequentialTransition(ball , growthTimeline , shrinkTimeline);
        sequentialTransition.setCycleCount(-1);
        sequentialTransition.play();
        sizeChangeAnimation = sequentialTransition;
        ball.setPhase2Animation(this);
    }
}
