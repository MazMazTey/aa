package controller;

import view.ProfileMenu;
import view.RegisterMenu;
import view.Settings;

import static javafx.application.Platform.exit;

public class MainMenuController {
    public void startNewGame() {

    }

    public void continueGame() {

    }

    public void enterProfileMenu() throws Exception {
        new ProfileMenu().start(RegisterMenu.stage);
    }

    public void leaderBoard() {

    }

    public void settings() throws Exception {
        new Settings().start(RegisterMenu.stage);
    }

    public void Exit() {
        // TODO save users to gson file
        exit();
    }
}
