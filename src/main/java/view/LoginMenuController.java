package view;

import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuController {
    private final LoginController controller;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public LoginMenuController() {
        this.controller = new LoginController();
    }

    public void login(MouseEvent mouseEvent) throws Exception {
        String username = this.username.getText();
        String password = this.password.getText();
        String result = controller.login(username , password);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (result) {
            case "this user doesn't exist" -> {
                alert.setTitle("Error!");
                alert.setHeaderText("Login Error!");
                alert.setContentText("This user doesn't exist!");
                alert.showAndWait();
            }
            case "password incorrect" -> {
                alert.setTitle("Error!");
                alert.setHeaderText("Login Error!");
                alert.setContentText("Wrong password!");
                alert.showAndWait();
            }
            case "login successful" -> new MainMenu().start(RegisterMenu.stage);
        }
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }
}
