package controller;

import model.AA;
import view.LoginMenu;
import view.MainMenu;
import view.RegisterMenu;

import java.io.IOException;
import java.net.URL;

public class ProfileController {
    public void logout() throws IOException {
        AA.setLoggedInUser(null);
        new LoginMenu().start(RegisterMenu.stage);
    }

    public void deleteAccount() throws Exception {
        AA.getAllUsers().remove(AA.getLoggedInUser().getUsername());
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

    public void setAvatar(URL avatarPath) {
        AA.getLoggedInUser().setAvatarPath(avatarPath);
    }

    public void back() throws Exception {
        new MainMenu().start(RegisterMenu.stage);
    }

    public void customAvatar() {
        // TODO implement avatar selection from file
    }
}
