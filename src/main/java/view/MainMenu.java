package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AA;

import java.net.URL;

public class MainMenu extends Application {
    private final MainMenuController controller;

    public MainMenu() {
        this.controller = new MainMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AA.getMediaPlayer().setAutoPlay(true);

        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        VBox vBox = (VBox) borderPane.getChildren().get(0);
        Button newGameButton = new Button("New Game");
        vBox.getChildren().add(1 , newGameButton);

        Label label1 = new Label();
        Slider totalBalls = new Slider(8 , 14 , 10);
        Label label2 = new Label();
        Slider initBalls = new Slider(5 , 9 , 5);
        VBox initVBox = new VBox(new Label("Initial Balls") , new HBox(initBalls , label2));
        initVBox.setAlignment(Pos.CENTER);
        initVBox.setSpacing(10);
        VBox totalVBox = new VBox(new Label("Total Balls") , new HBox(totalBalls , label1));
        totalVBox.setAlignment(Pos.CENTER);
        totalVBox.setSpacing(10);
        HBox hBox = new HBox(initVBox , totalVBox);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(2 , hBox);


        setUpSlider(label1, totalBalls);

        setUpSlider(label2, initBalls);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.startNewGame((int) Math.floor(totalBalls.getValue()) , (int) Math.floor(initBalls.getValue()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private void setUpSlider(Label label1, Slider totalBalls) {
        totalBalls.setMaxWidth(150);
        totalBalls.setBlockIncrement(1.0);
        totalBalls.setMajorTickUnit(1.0);
        totalBalls.setShowTickLabels(true);
        totalBalls.setShowTickMarks(true);
        totalBalls.setSnapToTicks(true);
        totalBalls.valueProperty().addListener((observable , oldValue , newValue) ->
                label1.setText((int) Math.floor(newValue.doubleValue()) + ""));
    }
}
