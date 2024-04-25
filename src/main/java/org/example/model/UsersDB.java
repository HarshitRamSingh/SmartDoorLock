package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class UsersDB {
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UsersDB() {
        this.users = new ArrayList<>();
    }

    public UsersDB(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
        System.out.println(user.getUserID() + " added to the database.");
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public User getUser(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByRfid(String rfidData) {
        for (User user : users) {
            if (user.getRfidData().equals(rfidData)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByFingerPrint(String fingerPrintData) {
        for (User user : users) {
            if (user.getFingerPrintData().equals(fingerPrintData)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByFace(String faceData) {
        for (User user : users) {
            if (user.getFaceData().equals(faceData)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUserExist(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByRfid(String rfidData) {
        for (User user : users) {
            if (user.getRfidData().equals(rfidData)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByFingerPrint(String fingerPrintData) {
        for (User user : users) {
            if (user.getFingerPrintData().equals(fingerPrintData)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExistByFace(String faceData) {
        for (User user : users) {
            if (user.getFaceData().equals(faceData)) {
                return true;
            }
        }
        return false;
    }
}
