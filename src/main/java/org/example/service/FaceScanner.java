package org.example.service;

import org.example.model.Face;

import javax.swing.*;
import java.awt.*;

public class FaceScanner extends Thread {
    int faceAuthID;
    JPanel faceScanPanel;
    JLabel faceStatusLabel;
    String face;
    boolean start;
    public FaceScanner(int authID){
        faceAuthID = authID;
        face = "";
        faceScanPanel = new JPanel();
        faceStatusLabel = new JLabel("face");
        faceScanPanel.setBackground(Color.GRAY);
        faceScanPanel.add(faceStatusLabel);
        faceScanPanel.setVisible(true);
    }

    @Override
    public void run() {
        System.out.println("Current face data: " + face);
        while(true){
            try { this.sleep(5000); } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setFace(String newFace){
        face = newFace;
    }
    public boolean getStart(){
        return start;
    }
    public void setStart(boolean b){
        start = b;
    }

    public Face scanFace(){
        return new Face(face);
    }

    public JPanel getFaceScanPanel(){
        return faceScanPanel;
    }
}
