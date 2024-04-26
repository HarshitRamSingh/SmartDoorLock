package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

public class Attempt {
    private static int nextAttemptID = 1;
    private static int maxFailedAttempts = 3;
    private LocalDateTime attemptDateTime;
    private String authType;
    private int attemptID;
    private String userID;
    private Map<String, Integer> userFailedAttempts;
    private int failedAttempts;
    private String attemptTime;
    private String attemptDate;
    private String attemptStatus;

    public int getAttemptID() {
        return this.attemptID;
    }

    public void setAttemptID(int attemptID) {
        this.attemptID = attemptID;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAttemptTime() {
        return this.attemptTime;
    }

    public void setAttemptTime(String attemptTime) {
        this.attemptTime = attemptTime;
    }

    public String getAttemptDate() {
        return this.attemptDate;
    }

    public void setAttemptDate(String attemptDate) {
        this.attemptDate = attemptDate;
    }

    public String getAttemptStatus() {
        return this.attemptStatus;
    }

    public void setAttemptStatus(String attemptStatus) {
        this.attemptStatus = attemptStatus;
    }

    public Attempt() {
        this.userFailedAttempts = new HashMap<>();
    }

    public int getFailedAttempts() {
        return this.failedAttempts;
    }
    //
    public void incrementFailedAttempts() {
        this.failedAttempts++;
    }

    public int getFailedAttempts(String userID) {
        return this.userFailedAttempts.getOrDefault(userID, 0);
    }

    public void incrementFailedAttempts(String userID) {
        this.userFailedAttempts.put(userID, getFailedAttempts(userID) + 1);
    }

//    public Attempt(int attemptID, String userID, String attemptTime, String attemptDate, String attemptStatus) {
//        this.attemptID = attemptID;
//        this.userID = userID;
//        this.attemptTime = attemptTime;
//        this.attemptDate = attemptDate;
//        this.attemptStatus = attemptStatus;
//    }

    public Attempt(String userID, boolean result, String authType) {
        this.attemptID = nextAttemptID++;
        this.userID = userID;
        this.authType = authType;
        this.attemptStatus = result ? "Success" : "Failed";
        this.attemptDateTime = LocalDateTime.now();
    }
}
