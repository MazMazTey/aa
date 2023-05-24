package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AA {
    private static User loggedInUser;
    private static final HashMap<String , User> allUsers = new HashMap<>();
    private static ArrayList<User> allUsersList = new ArrayList<>();

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
//        for (int i = 0 ; i < allUsersList.size() ; i++) {
//            System.out.println(allUsersList.get(i).getHighScore());
//        }
        return allUsersList;
    }
}
