package model.database;

import model.User;

import java.util.ArrayList;

public class Data {
    private ArrayList<User> allUsers;

    public Data(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }
}
