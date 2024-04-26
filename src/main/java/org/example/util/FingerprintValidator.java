package org.example.util;
import org.example.model.*;

public class FingerprintValidator {

    public FingerprintValidator() { }
    public static boolean authenticateFingerprint(String userID, String receivedFingerprintData, UsersDB authorizedUsers, TryCounter tryCounter) {
        // check for invalid input
        switch (receivedFingerprintData) {
            case "Finger not placed properly" -> {
                System.out.println("Please place your finger properly on the sensor.");
                return false;
            } case "Fingerprint sensor is dirty" -> {
                System.out.println("Please clean the fingerprint sensor and try again.");
                return false;
            }
            case "Empty" -> {
                System.out.println("No input provided.");
                return false;
            }
            default -> {
                // iterate through authorized users comparing fingerprint data
                for(User user: authorizedUsers.getUsers()){
                    if(user.getFingerPrintData().equals(receivedFingerprintData)){
                        System.out.println("Fingerprint Verified.");
                        return true;
                    }
                }
                tryCounter.incrementFailedAttempts(userID);
                System.out.println("Invalid fingerprint.");
                return false;
            }
        }
    }
}

