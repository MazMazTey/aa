package controller;

import model.Game;
import model.database.DataUtilities;
import view.*;

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

    public void leaderBoard() throws Exception {
        new LeaderBoard().start(RegisterMenu.stage);
    }

    public void settings() throws Exception {
        new Settings().start(RegisterMenu.stage);
    }

    public void Exit() throws IOException {
        DataUtilities.pushData();
        System.exit(0);
    }
}
