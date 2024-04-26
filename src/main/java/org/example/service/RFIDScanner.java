package org.example.service;

import org.example.model.RFID;

import javax.swing.*;
import java.awt.*;

public class RFIDScanner extends Thread {
    int rfidAuthID;
    JPanel rfidScanPanel;
    JLabel rfidStatusLabel;
    String rfid;
    public RFIDScanner(int authID){
        rfidAuthID = authID;
        rfid = "";
        rfidScanPanel = new JPanel();
        rfidStatusLabel = new JLabel("rfid");
        rfidScanPanel.setBackground(Color.WHITE);
        rfidScanPanel.add(rfidStatusLabel);
        rfidScanPanel.setVisible(true);
    }

    @Override
    public void run() {
        System.out.println("Current rfid data: " + rfid);
        while(true){
            try { this.sleep(5000); } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setRFID(String newRFID){
        rfid = newRFID;
    }

    public RFID scanRFID(){
        return new RFID(rfid);
    }

    public JPanel getRFIDScanPanel(){
        return rfidScanPanel;
    }
}
