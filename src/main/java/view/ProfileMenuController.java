package view;

import controller.ProfileController;
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
import java.util.Optional;

public class ProfileMenuController {
    private final ProfileController controller;

    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Rectangle rectangle;

    public ProfileMenuController() {
        this.controller = new ProfileController();
    }

    @FXML
    public void initialize() {
        rectangle = new Rectangle(30 , 30);
        rectangle.setFill(new ImagePattern(
                new Image(ProfileMenu.class.getResource(
                        "/Images/car.jpg").toExternalForm())));
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

    public void setAvatar(MouseEvent mouseEvent) {
        controller.setAvatar(null);
        // TODO get clicked image
    }
}
