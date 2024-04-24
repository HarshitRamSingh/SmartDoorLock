package org.example.service;

import org.example.model.UsersDB;
import org.example.util.RFIDValidator;

public class RFIDAuthentication {
    private RFIDValidator rfidValidator;

    public RFIDAuthentication(UsersDB usersDB) {
        this.rfidValidator = new RFIDValidator(usersDB);
    }

    public boolean authenticateRFID(String userID, String receivedRFIDData, UsersDB usersDB) {
        return rfidValidator.authenticateRFID(userID, receivedRFIDData, usersDB);
    }
}
