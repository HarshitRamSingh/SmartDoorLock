package org.example.service;

import org.example.controller.Door;
import org.example.model.*;

public class AuthenticationSystem {
    private int systemID;
    private int userID;
    UsersDB authorizedUsers;
    Door door;
    FingerprintAuthenticator fingerprintAuth;
    FaceAuthenticator faceAuth;
    RFIDAuthenticator rfidAuth;
    TryCounter tryCounter;
    //Timeout timeout;
    Logger logger;

    public AuthenticationSystem(int id, UsersDB users){
        systemID = id;
        authorizedUsers = users;
        fingerprintAuth = new FingerprintAuthenticator(1);
        faceAuth = new FaceAuthenticator(1);
        rfidAuth = new RFIDAuthenticator(1);
        door = new Door(1);
    }

    public void unlockDoor(){
        door.setLocked(false);
    }
    public void lockDoor(){
        door.setLocked(true);
    }
    public boolean validateUser(){
        verifyFingerprint();
        verifyFace();
        verifyRFID();
        return true;
    }

    public boolean verifyFingerprint(){
        // scan fingerprint
        Fingerprint scan = fingerprintAuth.scanFingerprint();
        // iterate through authorized users comparing fingerprint data
        for(User user: authorizedUsers.getUsers()){
            if(user.getFingerPrintData().equals(scan.getFingerprintData() + "")){
                System.out.println("Fingerprint Verified.");
                return true;
            }
        }
        System.out.println("Invalid fingerprint.");
        return false;
    }

    public boolean verifyFace(){
        // scan face
        Face scan = faceAuth.scanFace();
        // iterate through authorized users comparing face data
        for(User user: authorizedUsers.getUsers()){
            if(user.getFaceData().equals(scan.getFaceData() + "")){
                System.out.println("Face Verified.");
                return true;
            }
        }
        System.out.println("Invalid face.");
        return false;
    }

    public boolean verifyRFID(){
        // scan RFID
        RFID scan = rfidAuth.scanRFID();
        // iterate through authorized users comparing rfid data
        for(User user: authorizedUsers.getUsers()){
            if(user.getRfidData().equals(scan.getRfidData() + "")){
                System.out.println("RFID Verified.");
                return true;
            }
        }
        System.out.println("Invalid RFID.");
        return false;
    }
}
