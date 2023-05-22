package controller;

import model.AA;
import model.User;

public class RegisterController {
    public String register(String username , String password) {
        if (AA.userExists(username)) return "user already exists";
        User user = new User(username , password, false);
        AA.addUser(user);
        return "register successful";
    }
}
