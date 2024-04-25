package org.example.service;

import org.example.model.Face;
import org.example.model.Fingerprint;
import org.example.model.UsersDB;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAuthenticator {
    private FaceAuthentication faceAuthentication;
    private FingerprintAuthentication fingerprintAuthentication;
    private String userID;
    private String faceData;
    private String fingerprintData;

    private UsersDB usersDB;

    public ParallelAuthenticator(FaceAuthentication faceAuthentication, FingerprintAuthentication fingerprintAuthentication, String userID, Face faceData, Fingerprint fingerprintData, UsersDB usersDB) {
        this.faceAuthentication = faceAuthentication;
        this.fingerprintAuthentication = fingerprintAuthentication;
        this.userID = userID;
        this.faceData = faceData.getFaceData();
        this.fingerprintData = fingerprintData.getFingerprintData();
        this.usersDB = usersDB;
    }

    public boolean authenticate() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Boolean> faceAuth = executor.submit(() -> faceAuthentication.authenticateFace(userID, faceData, usersDB));
        Future<Boolean> fingerprintAuth = executor.submit(() -> fingerprintAuthentication.authenticateFingerprint(userID, fingerprintData, usersDB));
        return faceAuth.get() || fingerprintAuth.get();
    }
}
