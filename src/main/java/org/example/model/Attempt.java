package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Attempt {
    private int attemptID;
    private int userID;
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

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
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

    public Attempt(int attemptID, int userID, String attemptTime, String attemptDate, String attemptStatus) {
        this.attemptID = attemptID;
        this.userID = userID;
        this.attemptTime = attemptTime;
        this.attemptDate = attemptDate;
        this.attemptStatus = attemptStatus;
    }
}
