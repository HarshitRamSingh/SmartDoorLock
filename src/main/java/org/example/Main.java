package org.example;

import org.example.controller.DoorController;
import org.example.model.Attempt;
import org.example.model.UsersDB;
import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;
import org.example.util.CSVReader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello and welcome!");
        UsersDB usersDB = new UsersDB();
        Attempt attempt = new Attempt();
        RFIDAuthentication rfidAuthentication = new RFIDAuthentication(usersDB, attempt);
        FaceAuthentication faceAuthentication = new FaceAuthentication(usersDB, attempt);
        FingerprintAuthentication fingerprintAuthentication = new FingerprintAuthentication(usersDB, attempt);
        DoorController doorController = new DoorController(rfidAuthentication, faceAuthentication, fingerprintAuthentication, usersDB, attempt);
        try {
            Iterator<List<String>> rowIterator = CSVReader.readCSV("src/main/resources/TestData.csv");
            while (rowIterator.hasNext()) {
                doorController.processUser(rowIterator.next());
            }
            System.out.println("End of CSV file reached. Terminating program.");
            System.exit(0);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}