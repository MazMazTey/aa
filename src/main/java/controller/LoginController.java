package controller;

import model.AA;
import model.User;

public class LoginController {
    public String login(String username, String password) {
        if (!AA.userExists(username)) return "this user doesn't exist";
        User user = AA.getUserByUsername(username);
        if (!user.getPassword().equals(password)) return "password incorrect";
        AA.setLoggedInUser(user);
        return "login successful";
    }
}
