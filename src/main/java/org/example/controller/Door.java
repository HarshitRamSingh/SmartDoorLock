package org.example.controller;

public class Door {
    private int doorID;
    private boolean locked = true;

    public Door(int id){
        doorID = id;
    }

    public boolean getLocked(){
        return locked;
    }

    public int getDoorID(){
        return doorID;
    }

    public void setLocked(boolean newLock){
        locked = newLock;
    }

    public void setDoorID(int newID){
        doorID = newID;
    }
}
