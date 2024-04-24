package org.example.model;

public class TryCounter {
    // Write code such that this class maintains the number of failed attempts to unlock the door. If the failed attempts exceed 3 push the latest time and date to timeout component
    private int failedAttempts;

    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public TryCounter() {
    }

    public TryCounter(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
    }

    public void resetFailedAttempts() {
        this.failedAttempts = 0;
    }

    public void pushToTimeout() {
        // push the latest time and date to timeout component
        // Write code here

    }

    public void checkFailedAttempts() {
        if (this.failedAttempts > 3) {
            pushToTimeout();
        }
    }

    public void unlockDoor() {
        resetFailedAttempts();
    }

    public void lockDoor() {
        incrementFailedAttempts();
        checkFailedAttempts();
    }

    public void displayFailedAttempts() {
        System.out.println("Failed attempts: " + this.failedAttempts);
    }
}
