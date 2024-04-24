package org.example.service;

import org.example.model.UsersDB;
import org.example.util.FaceValidator;

public class FaceAuthentication {
    private UsersDB usersDB;

    public FaceAuthentication(UsersDB usersDB) {
        this.usersDB = usersDB;
    }

    // Method to perform face authentication
    public boolean authenticateFace(String userID, String receivedFaceData, UsersDB usersDB1) {
        return FaceValidator.authenticateFace(userID, receivedFaceData, usersDB1);
    }
}
