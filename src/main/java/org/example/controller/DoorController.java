package org.example.controller;

import org.example.model.User;
import org.example.model.UsersDB;
import org.example.service.ParallelAuthenticator;


import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.example.model.Attempt;

public class DoorController {
    private RFIDAuthentication rfidAuthentication;
    private FaceAuthentication faceAuthentication;
    private FingerprintAuthentication fingerprintAuthentication;
    private Attempt attempt = new Attempt();

    private UsersDB usersDB = new UsersDB();

    public DoorController(RFIDAuthentication rfidAuthentication, FaceAuthentication faceAuthentication, FingerprintAuthentication fingerprintAuthentication) {
        this.rfidAuthentication = rfidAuthentication;
        this.faceAuthentication = faceAuthentication;
        this.fingerprintAuthentication = fingerprintAuthentication;
    }

    public boolean authenticateRFID(String userID, String receivedRFIDData) {
        return rfidAuthentication.authenticateRFID(userID, receivedRFIDData, usersDB);
    }

    public boolean authenticateFace(String userID, String receivedFaceData) {
        return faceAuthentication.authenticateFace(userID, receivedFaceData, usersDB);
    }

    public boolean authenticateFingerprint(String userID, String receivedFingerprintData) {
        return fingerprintAuthentication.authenticateFingerprint(userID, receivedFingerprintData, usersDB);
    }

    public void processUser(List<String> row) throws ExecutionException, InterruptedException {
        String userType = row.get(0);
        if (userType.equals("Admin")) {
            String adminAction = row.get(1);
            if (adminAction.equals("addUser")) {
                String userID = row.get(2);
                String rfidData = row.get(3);
                String faceData = row.get(4);
                String fingerprintData = row.get(5);
                System.out.println(fingerprintData);
                User user = new User(userID, rfidData, faceData, fingerprintData);
                usersDB.addUser(user);
            }
        } else if (userType.equals("User")) {
            String userID = row.get(2);
            String rfidData = row.get(3);
            String faceData = row.get(4);
            String fingerprintData = row.get(5);
            // get the attempt object for the user
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
