package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.AA;

import java.io.IOException;

public class MainMenuController {
    private final controller.MainMenuController controller;

    public MainMenuController() {
        controller = new controller.MainMenuController();
    }

    public void startNewGame(boolean is2Player , int totalBalls , int initBalls) throws Exception {
        controller.startNewGame(is2Player , totalBalls , initBalls);
    }

    public void continueGame(MouseEvent mouseEvent) {
        controller.continueGame();
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        if (AA.getLoggedInUser().getUsername().equals("guest")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot enter profile menu");
            alert.setContentText("Guests cannot enter profile menu");
            alert.showAndWait();
            return;
        }
        controller.enterProfileMenu();
    }

    public void leaderBoard(MouseEvent mouseEvent) throws Exception {
        controller.leaderBoard();
    }

    public void settings(MouseEvent mouseEvent) throws Exception {
        controller.settings();
    }

    public void Exit(MouseEvent mouseEvent) throws IOException {
        controller.Exit();
    }
}
