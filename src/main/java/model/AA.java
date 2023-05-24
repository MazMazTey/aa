package model;

import java.util.ArrayList;
import java.util.HashMap;

public class AA {
    private static User loggedInUser;
    private static final HashMap<String , User> allUsers = new HashMap<>();

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
    }

    public static HashMap<String , User> getAllUsers() {
        return allUsers;
    }
}
