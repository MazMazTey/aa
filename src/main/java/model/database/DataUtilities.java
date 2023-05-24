package model.database;

import com.google.gson.Gson;
import model.AA;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataUtilities {
    public static void fetchData() throws FileNotFoundException {
        File database = new File("src/main/resources/DataBase/data.json");
        Scanner dataScanner = new Scanner(database);
        if (!dataScanner.hasNextLine()) return;

        Gson gson = new Gson();
        String fileLine = dataScanner.nextLine();
        Data fetchedData = gson.fromJson(fileLine , Data.class);
        Data fetchedData1 = gson.fromJson(fileLine , Data.class);

        ArrayList<User> usersToBeAdded = fetchedData.getAllUsers();
        AA.addAllUsers(usersToBeAdded);
    }

    public static void pushData() throws IOException {
        ArrayList<User> allUsers = new ArrayList<>(AA.getAllUsers().values());
        FileWriter fileWriter = new FileWriter("src/main/resources/DataBase/data.json");
        Gson gson = new Gson();
        Data dataToBePushed = new Data(allUsers);
        String dataLine = gson.toJson(dataToBePushed);
        fileWriter.write(dataLine);
        fileWriter.close();
    }
}
