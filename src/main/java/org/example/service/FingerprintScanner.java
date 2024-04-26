package org.example.service;

import org.example.model.Fingerprint;

import javax.swing.*;
import java.awt.*;

public class FingerprintScanner extends Thread {
    int fingerprintAuthID;
    JPanel fingerprintScanPanel;
    JLabel fingerprintStatusLabel;
    String finger;
    boolean start = false;
    public FingerprintScanner(int authID){
        fingerprintAuthID = authID;
        finger = "";
        fingerprintScanPanel = new JPanel();
        fingerprintStatusLabel = new JLabel("finger");
        fingerprintScanPanel.setBackground(Color.GRAY);
        fingerprintScanPanel.add(fingerprintStatusLabel);
        fingerprintScanPanel.setVisible(true);
    }

    @Override
    public void run() {
        System.out.println("Current fingerprint data: " + finger);
        while(true){
            try { this.sleep(5000); } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setFinger(String newFinger){
        finger = newFinger;
    }

    public Fingerprint scanFingerprint(){
        return new Fingerprint(finger);
    }

    public boolean getStart(){
        return start;
    }
    public void setStart(boolean b){
        start = b;
    }

    public JPanel getFingerprintScanPanel(){
        return fingerprintScanPanel;
    }
}
