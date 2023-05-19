package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URL;

public class ProfileMenu extends Application {
    @FXML
    private Rectangle rectangle;
    BorderPane borderPane;
    @Override
    public void start(Stage stage) throws Exception {
        URL url = ProfileMenu.class.getResource("/FXML/ProfileMenu.fxml");
        borderPane = FXMLLoader.load(url);
        Scene scene = new Scene(borderPane);
        URI uri = ProfileMenu.class.getResource("/Images/car.jpg").toURI();
        File file = new File(uri);
        Image image = new Image(file.toURI().toString());
        rectangle = new Rectangle(30 , 30);
        rectangle.setFill(new ImagePattern(new Image(ProfileMenu.class.getResource("/Images/car.jpg").toExternalForm())));
        ImageView imageView = new ImageView(image);
        borderPane.getChildren().add(rectangle);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        rectangle = new Rectangle(30 , 30 , 30 , 30);
        rectangle.setFill(new ImagePattern(
                new Image(ProfileMenu.class.getResource(
                        "/Images/car.jpg").toExternalForm())));
        //borderPane.getChildren().add(rectangle);
    }
}
