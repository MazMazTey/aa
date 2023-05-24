package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.database.DataUtilities;

import java.net.URL;

public class RegisterMenu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        RegisterMenu.stage = stage;
        URL url = RegisterMenu.class.getResource("/FXML/RegisterMenu.fxml");
        BorderPane borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        stage.setTitle("aa");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        DataUtilities.fetchData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
