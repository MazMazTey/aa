package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.AA;

public class SelectSong extends Application {
    public ToggleGroup selectSong;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane songBorderPane = FXMLLoader.load(getClass().
                getResource("/FXML/SelectSong.fxml"));
        Scene scene = new Scene(songBorderPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        RadioButton radioButton1 = (RadioButton) selectSong.getToggles().get(0);
        RadioButton radioButton2 = (RadioButton) selectSong.getToggles().get(1);
        RadioButton radioButton3 = (RadioButton) selectSong.getToggles().get(2);
        setSong(radioButton1, radioButton2, radioButton3);
        setSong(radioButton2, radioButton1, radioButton3);
        setSong(radioButton3, radioButton2, radioButton1);
    }

    private void setSong(RadioButton radioButton1, RadioButton radioButton2
            , RadioButton radioButton3) {
        radioButton1.selectedProperty().addListener(
                (observable , oldValue , newValue) -> {
                    if (newValue) {
                        radioButton2.setSelected(false);
                        radioButton3.setSelected(false);
                        String songName = radioButton1.getText();
                        AA.getMediaPlayer().stop();
                        switch (songName) {
                            case "Song 1" -> {
                                Media media = AA.getAllSongs()[0];
                                AA.setCurrentSong(media);
                                AA.setMediaPlayer(new MediaPlayer(media));
                                AA.getMediaPlayer().setAutoPlay(true);
                                AA.getMediaPlayer().setCycleCount(-1);
                                AA.getMediaPlayer().play();
                            }
                            case "Song 2" -> {
                                Media media = AA.getAllSongs()[1];
                                AA.setCurrentSong(media);
                                AA.setMediaPlayer(new MediaPlayer(media));
                                AA.getMediaPlayer().setAutoPlay(true);
                                AA.getMediaPlayer().setCycleCount(-1);
                                AA.getMediaPlayer().play();
                            }
                            case "Song 3" -> {
                                Media media = AA.getAllSongs()[2];
                                AA.setCurrentSong(media);
                                AA.setMediaPlayer(new MediaPlayer(media));
                                AA.getMediaPlayer().setAutoPlay(true);
                                AA.getMediaPlayer().setCycleCount(-1);
                                AA.getMediaPlayer().play();
                            }
                        }
                    }
                });
    }
}
