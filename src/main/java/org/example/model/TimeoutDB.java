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

    private Map<Integer, LocalDateTime> timeouts;

    public TimeoutDB() {
        this.timeouts = new HashMap<>();
    }

    public void addTimeout(int userID) {
        this.timeouts.put(userID, LocalDateTime.now().plusSeconds(300));
    }

    public boolean isTimeout(int userID) {
        if (!this.timeouts.containsKey(userID)) {
            return false;
        }
        LocalDateTime timeoutEnd = this.timeouts.get(userID);
        return LocalDateTime.now().isBefore(timeoutEnd);
    }

    public TimeoutDB(int timeoutID, int timeoutData) {
        this.timeoutID = timeoutID;
        this.timeoutData = timeoutData;
    }
}
