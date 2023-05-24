package view;

import controller.ProfileController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;

public class ProfileMenuController {
    private final ProfileController controller;

    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Rectangle rectangle1 , rectangle2 , rectangle3;
    private URL url1 , url2 , url3;

    public ProfileMenuController() {
        this.controller = new ProfileController();
    }

    @FXML
    public void initialize() {
        url1 = ProfileMenu.class.getResource(
                "/Images/avatar0.jpg");
        rectangle1.setFill(new ImagePattern(
                new Image(url1.toExternalForm())));
        url2 = ProfileMenu.class.getResource(
                "/Images/avatar1.jpg");
        rectangle2.setFill(new ImagePattern(
                new Image(url2.toExternalForm())));
        url3 = ProfileMenu.class.getResource(
                "/Images/avatar2.jpg");
        rectangle3.setFill(new ImagePattern(
                new Image(url3.toExternalForm())));
        rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.setAvatar(url1);
            }
        });
        rectangle2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.setAvatar(url2);
            }
        });
        rectangle3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                controller.setAvatar(url3);
            }
        });
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Logout Confirmation");
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) controller.logout();
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Account Deletion Confirmation");
        alert.setContentText("Are you sure you want to delete this account?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) controller.deleteAccount();
    }

    public void changeUsername(MouseEvent mouseEvent) {
        String username = newUsername.getText();
        String result = controller.changeUsername(username);
        Alert alert;
        switch (result) {
            case "this username is already taken" -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username change fail");
                alert.setContentText("This username is already taken");
                alert.showAndWait();
            }
            case "username change successful" -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Username change successful");
                alert.setContentText("Username changed successfully");
                alert.showAndWait();
            }
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        String password = newPassword.getText();
        controller.changePassword(password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Password change successful");
        alert.setContentText("Password changed successfully");
        alert.showAndWait();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        controller.back();
    }

    public void randomAvatar(MouseEvent mouseEvent) {
        int random = new Random().nextInt();
        random %= 3;
        random = Math.abs(random);
        switch (random) {
            case 0 -> controller.setAvatar(url1);
            case 1 -> controller.setAvatar(url2);
            case 2 -> controller.setAvatar(url3);
        }
    }

    public void customAvatar(MouseEvent mouseEvent) {
        controller.customAvatar();
    }
}
