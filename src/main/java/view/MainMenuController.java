package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.AA;

public class MainMenuController {
    private final controller.MainMenuController controller;

    public MainMenuController() {
        controller = new controller.MainMenuController();
    }

    public void startNewGame(MouseEvent mouseEvent) {
        controller.startNewGame();
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

    public void leaderBoard(MouseEvent mouseEvent) {
        controller.leaderBoard();
    }

    public void settings(MouseEvent mouseEvent) throws Exception {
        controller.settings();
    }

    public void Exit(MouseEvent mouseEvent) {
        controller.Exit();
    }
}
