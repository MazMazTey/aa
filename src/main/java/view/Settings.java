package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AA;

import java.net.URL;

public class Settings extends Application {
    public Button muteButton;
    @FXML
    private ToggleGroup difficulty;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = Settings.class.getResource("/FXML/Settings.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
        //TODO when opening settings it should show current difficulty
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
