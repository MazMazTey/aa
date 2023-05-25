package model;

import javafx.scene.media.Media;
import view.GameMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AA {
    private static User loggedInUser;
    private static final HashMap<String , User> allUsers = new HashMap<>();
    private static ArrayList<User> allUsersList = new ArrayList<>();
    private static final Media[] allSongs = {
            new Media(
                    GameMenu.class.getResource("/Media/music1.mp3").toExternalForm()),
            new Media(
                    GameMenu.class.getResource("/Media/music2.mp3").toExternalForm()),
            new Media(
                    GameMenu.class.getResource("/Media/music3.mp3").toExternalForm())};

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        AA.loggedInUser = loggedInUser;
    }

    public static void addUser(User user) {
        allUsers.put(user.getUsername() , user);
    }

    public static boolean userExists(String username) {
        return allUsers.containsKey(username);
    }

    public static User getUserByUsername(String username) {
        return allUsers.get(username);
    }

    public static void addAllUsers(ArrayList<User> usersToBeAdded) {
        for (User user : usersToBeAdded) {
            allUsers.put(user.getUsername() , user);
        }
        allUsersList = usersToBeAdded;
    }

    public static HashMap<String , User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<User> getAllUsersList() {
        return allUsersList;
    }

    public static ArrayList<User> leaderBoard() {
        Arrays.sort(allUsersList.toArray());
        return allUsersList;
    }

    public static Media[] getAllSongs() {
        return allSongs;
    }
}
