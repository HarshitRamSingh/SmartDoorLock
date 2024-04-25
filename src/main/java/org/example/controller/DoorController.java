package org.example.controller;

import org.example.model.*;
import org.example.service.ParallelAuthenticator;


import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DoorController {
    private RFIDAuthentication rfidAuthentication;
    private FaceAuthentication faceAuthentication;
    private FingerprintAuthentication fingerprintAuthentication;
    private Attempt attempt;
    private UsersDB usersDB;

    public DoorController(RFIDAuthentication rfidAuthentication, FaceAuthentication faceAuthentication, FingerprintAuthentication fingerprintAuthentication, UsersDB usersDB, Attempt attempt) {
        this.rfidAuthentication = rfidAuthentication;
        this.faceAuthentication = faceAuthentication;
        this.fingerprintAuthentication = fingerprintAuthentication;
        this.usersDB = usersDB;
        this.attempt = attempt;
    }
    public void processUser(List<String> row) throws ExecutionException, InterruptedException {
        String userType = row.get(0);
        if (userType.equals("Admin")) {
            String adminAction = row.get(1);
            if (adminAction.equals("addUser")) {
                String userID = row.get(2);
                RFID rfidData = new RFID(row.get(3));
                Face faceData = new Face(row.get(4));
                Fingerprint fingerprintData = new Fingerprint(row.get(5));
                User user = new User(userID, rfidData, faceData, fingerprintData);
                usersDB.addUser(user);
            } else if (adminAction.equals("modifyUser")) {
                String userID = row.get(2);
                RFID rfidData = new RFID(row.get(3));
                Face faceData = new Face(row.get(4));
                Fingerprint fingerprintData = new Fingerprint(row.get(5));
                User user = new User(userID, rfidData, faceData, fingerprintData);
                usersDB.modifyUser(user);
            } else if (adminAction.equals("deleteUser")) {
                String userID = row.get(2);
                usersDB.deleteUser(userID);
            } else if(adminAction.equals("viewUsers")){
                usersDB.viewUsers();
            } else {
                System.out.println("Invalid admin action");
            }
        } else if (userType.equals("User")) {
            String userID = row.get(2);
            RFID rfidData = new RFID(row.get(3));
            Face faceData = new Face(row.get(4));
            Fingerprint fingerprintData = new Fingerprint(row.get(5));
            System.out.println("Failed attempt number for userID " + userID + ": " + attempt.getFailedAttempts(userID));
            if (rfidAuthentication.authenticateRFID(userID, rfidData, usersDB)) {
                ParallelAuthenticator parallelAuthenticator = new ParallelAuthenticator(faceAuthentication, fingerprintAuthentication, userID, faceData, fingerprintData, usersDB);
                if (parallelAuthenticator.authenticate()) {
                    System.out.println("Success");
                }
            }
        }
    }
}
