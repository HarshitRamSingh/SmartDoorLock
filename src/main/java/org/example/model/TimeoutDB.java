package org.example.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TimeoutDB {
    private int timeoutID;
    private int timeoutData;

    public int getTimeoutID() {
        return this.timeoutID;
    }

    public void setTimeoutID(int timeoutID) {
        this.timeoutID = timeoutID;
    }

    public int getTimeoutData() {
        return this.timeoutData;
    }

    public void setTimeoutData(int timeoutData) {
        this.timeoutData = timeoutData;
    }

    private Map<String, LocalDateTime> timeouts;

    public TimeoutDB() {
        this.timeouts = new HashMap<>();
    }

    public void addTimeout(String userID) {
        System.out.println("Adding timeout for user " + userID + " till " + LocalDateTime.now().plusSeconds(300));
        this.timeouts.put(userID, LocalDateTime.now().plusSeconds(300));
    }

    public boolean isTimeout(String userID) {
        System.out.println("Checking if user " + userID + " is in timeout.");
        if(this.timeouts.containsKey(userID)) {
            System.out.println("Now time : " + LocalDateTime.now());;
            System.out.println("Timeout time :" + this.timeouts.get(userID));
            if (LocalDateTime.now().isBefore(this.timeouts.get(userID))) {
                System.out.println("User " + userID + " is still in timeout.");
                return true;
            } else {
                System.out.println("User " + userID + " has exited timeout.");
                this.timeouts.remove(userID);
                return false;
            }
        }
        else {
            return false;
        }
    }

    public TimeoutDB(int timeoutID, int timeoutData) {
        this.timeoutID = timeoutID;
        this.timeoutData = timeoutData;
    }
}
