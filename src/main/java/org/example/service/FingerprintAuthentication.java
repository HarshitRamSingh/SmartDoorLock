package org.example.service;

import org.example.model.UsersDB;
import org.example.util.FingerprintValidator;

public class FingerprintAuthentication {
    private UsersDB usersDB;

    public FingerprintAuthentication(UsersDB usersDB) {

        this.usersDB = usersDB;
    }

    // Method to perform face authentication
    public boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB usersDB1) {
        // Get the list of users from the UsersDB
        return FingerprintValidator.authenticateFingerprint(userID, receivedFingerprintData, usersDB1);
    }
}
