package org.example.model;

public class Face {
    private int faceID;
    private int faceData;

    public int getFaceID() {
        return this.faceID;
    }

    public void setFaceID(int faceID) {
        this.faceID = faceID;
    }

    public int getFaceData() {
        return this.faceData;
    }

    public void setFaceData(int faceData) {
        this.faceData = faceData;
    }

    public Face() {
    }

    public Face(int faceID, int faceData) {
        this.faceID = faceID;
        this.faceData = faceData;
    }
}
