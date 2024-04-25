package org.example.service;

import org.example.model.Face;
import org.example.model.RFID;

public class RFIDAuthenticator {
    int rfidAuthID;
    public RFIDAuthenticator(int authID){
        rfidAuthID = authID;
    }

    public RFID scanRFID(){
        return new RFID(0,0);
    }

}
