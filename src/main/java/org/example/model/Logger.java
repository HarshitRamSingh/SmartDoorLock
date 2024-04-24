package org.example.model;

public class Logger {
    private int logID;
    private String logData;

    public int getLogID() {
        return this.logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getLogData() {
        return this.logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public Logger() {
    }

    public Logger(int logID, String logData) {
        this.logID = logID;
        this.logData = logData;
    }
}
