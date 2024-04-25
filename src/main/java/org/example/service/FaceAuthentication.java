package org.example.service;

import org.example.model.Attempt;
import org.example.model.UsersDB;
import org.example.util.FaceValidator;

public class FaceAuthentication {
    private FaceValidator faceValidator;
    public FaceAuthentication(UsersDB usersDB, Attempt attempt) {
        this.faceValidator = new FaceValidator(usersDB, attempt);
    }
    public boolean authenticateFace(String userID, String receivedFaceData, UsersDB usersDB1) {
        return faceValidator.authenticateFace(userID, receivedFaceData, usersDB1);
    }
}
