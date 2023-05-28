package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AA;

import java.net.URL;

public class Settings extends Application {
    @FXML
    private Button muteButton;
    @FXML
    private ToggleGroup difficulty;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Settings.class.getResource("/FXML/Settings.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        VBox vBox = (VBox) borderPane.getChildren().get(0);
        HBox hBox = (HBox) vBox.getChildren().get(6);
        Button shootKey = new Button("Shoot");
        Button freezeKey = new Button("Freeze");
        Button pauseKey = new Button("Pause");
        Button moveLeftKey = new Button("Move Left");
        Button moveRightKey = new Button("Move Right");
        setKeyBinding(shootKey, borderPane);
        setKeyBinding(freezeKey, borderPane);
        setKeyBinding(pauseKey, borderPane);
        setKeyBinding(moveLeftKey, borderPane);
        setKeyBinding(moveRightKey, borderPane);

        hBox.getChildren().add(0 , shootKey);
        hBox.getChildren().add(1 , freezeKey);
        hBox.getChildren().add(2 , pauseKey);
        hBox.getChildren().add(3 , moveLeftKey);
        hBox.getChildren().add(4 , moveRightKey);
        stage.setScene(scene);
        stage.show();
    }

    private void setKeyBinding(Button binding , BorderPane root) {
        binding.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                binding.requestFocus();
                binding.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        String keyName = keyEvent.getCode().getName();
                        switch (binding.getText()) {
                            case "Shoot" -> AA.getLoggedInUser().setShootBallKey(keyName);
                            case "Freeze" -> AA.getLoggedInUser().setFreezeKey(keyName);
                            case "Pause" -> AA.getLoggedInUser().setPauseKey(keyName);
                            case "Move Left" -> AA.getLoggedInUser().setMoveLeftKey(keyName);
                            case "Move Right" -> AA.getLoggedInUser().setMoveRightKey(keyName);
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("Key Binding Change");
                        alert.setContentText("Key Binding Changed To " + keyName);
                        alert.showAndWait();
                    }
                });
            }
        });
    }

    @FXML
    public void initialize() {
        RadioButton radioButton1 = (RadioButton) difficulty.getToggles().get(0);
        RadioButton radioButton2 = (RadioButton) difficulty.getToggles().get(1);
        RadioButton radioButton3 = (RadioButton) difficulty.getToggles().get(2);
        setDifficulty(radioButton1, radioButton2, radioButton3);
        setDifficulty(radioButton2, radioButton1, radioButton3);
        setDifficulty(radioButton3, radioButton2, radioButton1);
    }

    private void setDifficulty(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3) {
        radioButton1.selectedProperty().addListener((observable , oldValue , newValue) -> {
            if (newValue) {
                radioButton2.setSelected(false);
                radioButton3.setSelected(false);
                String difficulty = radioButton1.getText();
                AA.getLoggedInUser().setDifficulty(difficulty);
            }
        });
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(RegisterMenu.stage);
    }

    public void muteSong(MouseEvent mouseEvent) {
        muteSong(muteButton);
    }

    public static void muteSong(Button muteButton) {
        if (!AA.isMusicMuted()) {
            AA.getMediaPlayer().setMute(true);
            muteButton.setText("Unmute Song");
            AA.setMusicMuted(true);
            return;
        }
        AA.getMediaPlayer().setAutoPlay(true);
        AA.getMediaPlayer().setMute(false);
        muteButton.setText("Mute Song");
        AA.setMusicMuted(false);
    }
}
