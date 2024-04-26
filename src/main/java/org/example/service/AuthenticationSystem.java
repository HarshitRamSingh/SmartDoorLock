package org.example.service;

import org.apache.poi.ss.formula.functions.T;
import org.example.controller.Door;
import org.example.model.*;
import org.example.util.FaceValidator;
import org.example.util.FingerprintValidator;
import org.example.util.RFIDValidator;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AuthenticationSystem {
    int systemID;
    String userID = "";
    UsersDB authorizedUsers;
    Door door;
    TryCounter tryCounter;
    //Timeout timeout;
    Logger logger;
    boolean rfidPassed;

    public AuthenticationSystem(int id, UsersDB users){
        rfidPassed = false;
        systemID = id;
        authorizedUsers = users;
        door = new Door(1);
        tryCounter = new TryCounter();
    }

    public void unlockDoor(){
        door.setLocked(false);
        System.out.println("Door Unlocked!");
    }
    public void lockDoor(){
        door.setLocked(true);
        System.out.println("Door Locked!");
    }
    public void setRfidPassed(boolean newPass){
        rfidPassed = newPass;
    }
    public boolean getRfidPassed(){
        return rfidPassed;
    }

    public boolean verifyRFID(RFID scan){
        return RFIDValidator.authenticateRFID("t", scan.getRfidData(), authorizedUsers, tryCounter);
    }

    public boolean verifyFace(Face scan) {
        return FaceValidator.authenticateFace("t", scan.getFaceData(), authorizedUsers, tryCounter);
    }

    public boolean verifyFinger(Fingerprint scan) {
        return FingerprintValidator.authenticateFingerprint("t", scan.getFingerprintData(), authorizedUsers, tryCounter);
    }
}
