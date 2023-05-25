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

import java.net.URL;

public class MainMenu extends Application {
    private final MainMenuController controller;

    public MainMenu() {
        this.controller = new MainMenuController();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        VBox vBox = (VBox) borderPane.getChildren().get(0);
        Button newGameButton = new Button("New Game");
        vBox.getChildren().add(1 , newGameButton);

        Label label = new Label();
        Slider slider = new Slider(8 , 14 , 10);
        HBox hBox = new HBox(slider, label);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(2 , hBox);


        slider.setMaxWidth(150);
        slider.setBlockIncrement(1.0);
        slider.setMajorTickUnit(1.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.valueProperty().addListener((observable , oldValue , newValue) ->
                label.setText((int) Math.floor(newValue.doubleValue()) + ""));
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    controller.startNewGame((int) Math.floor(slider.getValue()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }
}
