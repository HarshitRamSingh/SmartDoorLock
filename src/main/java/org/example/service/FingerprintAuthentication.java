package org.example.service;

import org.example.model.Attempt;
import org.example.model.UsersDB;
import org.example.util.FingerprintValidator;

public class FingerprintAuthentication {
    private FingerprintValidator fingerprintValidator;
    public FingerprintAuthentication(UsersDB usersDB, Attempt attempt) {
        this.fingerprintValidator = new FingerprintValidator(usersDB, attempt);
    }
    public boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB usersDB1) {
        return fingerprintValidator.authenticateFingerprint(userID, receivedFingerprintData, usersDB1);
    }
}
