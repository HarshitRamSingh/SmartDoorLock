package org.example.service;

import org.example.model.Attempt;
import org.example.model.RFID;
import org.example.model.UsersDB;
import org.example.util.RFIDValidator;

public class RFIDAuthentication {
    private RFIDValidator rfidValidator;
    public RFIDAuthentication(UsersDB usersDB, Attempt attempt) {
        this.rfidValidator = new RFIDValidator(usersDB, attempt);
    }
    public boolean authenticateRFID(String userID, RFID receivedRFIDData, UsersDB usersDB) {
        return rfidValidator.authenticateRFID(userID, receivedRFIDData.getRfidData(), usersDB);
    }
}
