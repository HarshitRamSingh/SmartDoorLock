package org.example.util;

import org.example.model.Attempt;
import org.example.model.User;
import org.example.model.UsersDB;

public class RFIDValidator {

    private static UsersDB usersDB;
    private static Attempt attempt;

    public RFIDValidator(UsersDB usersDB) {
        this.usersDB = usersDB;
    }
    public static boolean authenticateRFID(String userID, String receivedRFIDData, UsersDB usersDB1) {
        // Find the user with the given userID
        //print the size if the userList

        System.out.println("This is the size of the usersDB1: " + usersDB1.getUsers().size());

        for (User user : usersDB1.getUsers()) {
            if (user.getUserID().equals(userID) && user.getRfidData().equals(receivedRFIDData)) {
                System.out.println("RFID matched. Access granted.");
                return true;
            }
        }
        // If user with the given userID is not found
        // increment the failed attempts
        attempt.incrementFailedAttempts(userID);
        System.out.println("This is coming from the RFIDValidator class");
        // If user with the given userID is not found
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
