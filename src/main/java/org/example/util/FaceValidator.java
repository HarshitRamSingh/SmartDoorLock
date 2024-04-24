package org.example.util;

import java.util.List;
import java.util.Objects;

import org.example.model.User;
import org.example.model.UsersDB;

public class FaceValidator {
    public static boolean authenticateFace(String userID, String receivedFaceData, UsersDB usersDB) {
        // Find the user with the given userID
        for (User user : usersDB.getUsers()) {
            if (Objects.equals(user.getUserID(), userID)) {
                // Check if the received face data matches the stored face data
                System.out.println(user.getFaceData() + " " + receivedFaceData);
                if (Objects.equals(user.getFaceData(), receivedFaceData)) {
                    System.out.println("Face authentication successful for UserID: " + userID);
                    return true;
                } else {
                    System.out.println("Face authentication failed for UserID: " + userID);
                    return false;
                }
            }
        }
        System.out.println("This is coming from the FaceValidator class");

        // If user with the given userID is not found
        System.out.println("User with UserID " + userID + " not found.");
        return false;
    }
}
