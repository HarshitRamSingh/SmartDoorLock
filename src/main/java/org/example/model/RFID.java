package org.example.model;

public class RFID {
    private int rfidID;
    private int rfidData;

    public int getRfidID() {
        return this.rfidID;
    }

    public void setRfidID(int rfidID) {
        this.rfidID = rfidID;
    }

    public int getRfidData() {
        return this.rfidData;
    }

    public void setRfidData(int rfidData) {
        this.rfidData = rfidData;
    }

    public RFID() {
    }

    public RFID(int rfidID, int rfidData) {
        this.rfidID = rfidID;
        this.rfidData = rfidData;
    }
}
