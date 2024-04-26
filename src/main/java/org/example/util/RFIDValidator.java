package org.example.util;
import org.example.model.*;

public class RFIDValidator {

    public RFIDValidator() { }
    public static boolean authenticateRFID(String receivedRFIDData, UsersDB authorizedUsers) {
        // check for invalid input
        switch (receivedRFIDData) {
            case "RFID not placed properly" -> {
                System.out.println("Please place the RFID properly and try again.");
                return false;
            } case "RFID is dirty" -> {
                System.out.println("Please clean the RFID and try again.");
                return false;
            }
            case "Empty" -> {
                System.out.println("No input provided.");
                return false;
            }
            default -> {
                // iterate through authorized users comparing fingerprint data
                for(User user: authorizedUsers.getUsers()){
                    if(user.getRfidData().equals(receivedRFIDData)){
                        System.out.println("RFID Verified.");
                        return true;
                    }
                }
                System.out.println("Invalid RFID.");
                return false;
            }
        }
    }
}

