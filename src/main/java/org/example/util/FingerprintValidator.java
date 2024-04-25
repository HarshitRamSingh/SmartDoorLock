package org.example.util;

import java.util.List;
import java.util.Objects;

import org.example.model.Attempt;
import org.example.model.User;
import org.example.model.UsersDB;

public class FingerprintValidator {

    private static UsersDB usersDB;
    private static Attempt attempt;
    public FingerprintValidator(UsersDB usersDB, Attempt attempt) {
        this.usersDB = usersDB;
        this.attempt = attempt;
    }

    public static boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB usersDB1) {
        // Find the user with the given userID
        for (User user : usersDB1.getUsers()) {
            if (Objects.equals(user.getUserID(), userID)) {
                // Check if the received face data matches the stored face data
                if (Objects.equals(user.getFingerPrintData(), receivedFingerprintData)) {
                    System.out.println("Fingerprint authentication successful for UserID: " + userID);
                    return true;
                } else {
                    attempt.incrementFailedAttempts(userID);
                    System.out.println("Fingerprint authentication failed for UserID: " + userID);
                    return false;
                }
            }
        }
        attempt.incrementFailedAttempts(userID);
        System.out.println("This is coming from the FingerprintValidator class");

        // If user with the given userID is not found
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
