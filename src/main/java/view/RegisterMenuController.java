package view;

import controller.RegisterController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.AA;
import model.User;

import java.io.IOException;

public class RegisterMenuController {
    private final RegisterController controller;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public RegisterMenuController() {
        controller = new RegisterController();
    }

    public void register(MouseEvent mouseEvent) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        String result = controller.register(username , password);
        switch (result) {
            case "user already exists":
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Register Error");
                alert.setContentText("This user already exists");
                alert.showAndWait();
                break;
            case "register successful":
                new LoginMenu().start(RegisterMenu.stage);
                break;
        }
    }

    public void reset(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
    }

    public void guestLogin(MouseEvent mouseEvent) throws Exception {
        User guest = new User("guest" , "", true);
        AA.setLoggedInUser(guest);
        new MainMenu().start(RegisterMenu.stage);
    }

    public void startLoginMenu(MouseEvent mouseEvent) throws IOException {
        new LoginMenu().start(RegisterMenu.stage);
    }
}
