package org.example.util;
import org.example.model.*;

public class FaceValidator {

    public FaceValidator() { }
    public static boolean authenticateFace(String receivedFaceData, UsersDB authorizedUsers) {
        // check for invalid input
        switch (receivedFaceData) {
            case "Object too far away" -> {
                System.out.println("Please stand within the frame of the camera.");
                return false;
            } case "Camera is dirty" -> {
                System.out.println("Please clean the camera and try again.");
                return false;
            }
            case "Empty" -> {
                System.out.println("No input provided.");
                return false;
            }
            default -> {
                // iterate through authorized users comparing fingerprint data
                for(User user: authorizedUsers.getUsers()){
                    if(user.getFaceData().equals(receivedFaceData)){
                        System.out.println("Face Verified.");
                        return true;
                    }
                }
                System.out.println("Invalid Face.");
                return false;
            }
        }
    }
}
