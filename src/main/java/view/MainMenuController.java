package view;

import javafx.scene.input.MouseEvent;

public class MainMenuController {
    public void startNewGame(MouseEvent mouseEvent) {

    }

    public void continueGame(MouseEvent mouseEvent) {

    }

    public void enterProfileMenu(MouseEvent mouseEvent) {

    }

    public void leaderBoard(MouseEvent mouseEvent) {

    }

    public void settings(MouseEvent mouseEvent) throws Exception {
        new Settings().start(RegisterMenu.stage);
    }

    public void exit(MouseEvent mouseEvent) {
        //TODO save users to gson file

    }
}
