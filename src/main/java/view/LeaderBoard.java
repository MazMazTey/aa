package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AA;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        URL url = LeaderBoard.class.getResource("/FXML/LeaderBoard.fxml");
        BorderPane leaderBoardPane = FXMLLoader.load(url);
        Scene scene = new Scene(leaderBoardPane);
        VBox leaderBoard = new VBox();
        leaderBoardPane.setCenter(leaderBoard);
        leaderBoard.setSpacing(5);

        HBox description = new HBox();
        description.setSpacing(40);
        description.setAlignment(Pos.CENTER);
        description.getChildren().add(new Text("Rank"));
        description.getChildren().add(new Text("Username"));
        description.getChildren().add(new Text("High Score"));
        description.setBackground(new Background(new BackgroundFill(Color.BLUE , CornerRadii.EMPTY , Insets.EMPTY)));
        leaderBoard.getChildren().add(description);
        listUsers(leaderBoard);

        Button button = new Button("Back");
        BorderPane pane = new BorderPane();
        pane.setCenter(button);
        leaderBoard.getChildren().add(pane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new MainMenu().start(RegisterMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void listUsers(VBox leaderBoard) {
        ArrayList<User> list = AA.leaderBoard();
        Collections.reverse(list);
        for (int i = 0 ; i < list.size() && i < 10 ; i++) {
            addUserToLeaderBoard(list.get(i) , i + 1 , leaderBoard);
        }
    }

    private void addUserToLeaderBoard(User user , int rank , VBox leaderBoard) {
        HBox hBox = new HBox();
        hBox.setSpacing(40);
        hBox.setAlignment(Pos.CENTER);
        switch (rank) {
            case 1 ->
                    hBox.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
            case 2 ->
                    hBox.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
            case 3 ->
                    hBox.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        hBox.getChildren().add(new Text("" + rank));
        hBox.getChildren().add(new Text(user.getUsername()));
        hBox.getChildren().add(new Text("" + user.getHighScore()));
        leaderBoard.getChildren().add(hBox);
    }
}
