package controller;

import javafx.scene.image.Image;
import model.AA;
import view.LoginMenu;
import view.RegisterMenu;

import java.io.IOException;

public class ProfileController {
    public void logout() throws IOException {
        AA.setLoggedInUser(null);
        new LoginMenu().start(RegisterMenu.stage);
    }

    public void deleteAccount() throws Exception {
        AA.setLoggedInUser(null);
        new RegisterMenu().start(RegisterMenu.stage);
    }

    public String changeUsername(String username) {
        if (AA.userExists(username)) return "this username is already taken";
        AA.getLoggedInUser().setUsername(username);
        return "username change successful";
    }

    public void changePassword(String password) {
        AA.getLoggedInUser().setPassword(password);
    }

    public void setAvatar(Image avatar) {
        AA.getLoggedInUser().setAvatar(avatar);
    }
}
