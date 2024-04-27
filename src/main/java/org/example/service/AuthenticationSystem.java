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
import java.util.Timer;

public class AuthenticationSystem {
    int systemID;
    UsersDB authorizedUsers;
    Door door;
    TryCounter tryCounter;
    Logger logger;
    boolean rfidPassed;
    boolean deadlock;

    public AuthenticationSystem(int id, UsersDB users){
        rfidPassed = false;
        deadlock = false;
        systemID = id;
        authorizedUsers = users;
        door = new Door(1);
        tryCounter = new TryCounter();
    }
    public TryCounter getTryCounter(){
        return tryCounter;
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
    public boolean getDeadlock(){
        return deadlock;
    }

    public void timeout(){

    }

    public boolean verifyRFID(RFID scan){
        if(RFIDValidator.authenticateRFID(scan.getRfidData(), authorizedUsers)){
            return true;
        }
        else {
            if(tryCounter.incrementFailedAttempts('r')){
                deadlock = true;
            }
            return false;
        }
    }

    public boolean verifyFace(Face scan) {
        if(FaceValidator.authenticateFace(scan.getFaceData(), authorizedUsers)){
            return true;
        }
        else {
            if(tryCounter.incrementFailedAttempts('f')){
                deadlock = true;
            }
            return false;
        }
    }

    public boolean verifyFinger(Fingerprint scan) {
        if(FingerprintValidator.authenticateFingerprint(scan.getFingerprintData(), authorizedUsers)){
            return true;
        }
        else {
            if(tryCounter.incrementFailedAttempts('p')){
                deadlock = true;
            }
            return false;
        }
    }
}
