package controller;

import model.Game;
import model.database.DataUtilities;
import view.GameMenu;
import view.ProfileMenu;
import view.RegisterMenu;
import view.Settings;

import java.io.IOException;

public class MainMenuController {
    public void startNewGame() throws Exception {
        new GameMenu(new Game()).start(RegisterMenu.stage);
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

    public void Exit() throws IOException {
        // TODO save users to gson file
        DataUtilities.pushData();
        System.exit(0);
    }
}
