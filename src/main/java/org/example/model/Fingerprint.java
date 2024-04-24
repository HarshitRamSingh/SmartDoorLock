package org.example.model;

public class Fingerprint {
    private int fingerprintID;
    private int fingerprintData;

    public int getFingerprintID() {
        return this.fingerprintID;
    }

    public void setFingerprintID(int fingerprintID) {
        this.fingerprintID = fingerprintID;
    }

    public int getFingerprintData() {
        return this.fingerprintData;
    }

    public void setFingerprintData(int fingerprintData) {
        this.fingerprintData = fingerprintData;
    }

    public Fingerprint() {
    }

    public Fingerprint(int fingerprintID, int fingerprintData) {
        this.fingerprintID = fingerprintID;
        this.fingerprintData = fingerprintData;
    }
}
