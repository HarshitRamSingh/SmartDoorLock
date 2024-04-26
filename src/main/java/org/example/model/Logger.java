package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static int nextLogID = 1; // static variable
    private int logID;
    private List<Attempt> attempts;

    public Logger() {
        this.logID = nextLogID++; // increment logID with every new object
        this.attempts = new ArrayList<>();
    }

    public int getLogID() {
        return this.logID;
    }

    // remove the setLogID method as logID is now automatically set

    public List<Attempt> getAttempts() {
        return this.attempts;
    }

    public void addAttempt(Attempt attempt) {
        this.attempts.add(attempt);
    }
}
