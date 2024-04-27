package org.example.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.example.Main;
import org.example.model.UsersDB;
import org.example.service.*;

public class DoorSimulationPage extends JFrame implements ActionListener, KeyListener {
    UsersDB usersDB;
    JButton exitButton;
    JFrame doorFrame;
    JLabel doorStatusLabel;
    FaceScanner faceScanner;
    FingerprintScanner fingerprintScanner;
    RFIDScanner rfidScanner;
    AuthenticationSystem authSystem;

    public DoorSimulationPage(UsersDB users){

        /* Database and Auth */
        usersDB = users;
        faceScanner = new FaceScanner(1);
        fingerprintScanner = new FingerprintScanner(1);
        rfidScanner = new RFIDScanner(1);
        rfidScanner.start();
        authSystem = new AuthenticationSystem(1, usersDB);

        /* JFrame Setup */
        doorFrame = new JFrame(); // Create frame
        doorFrame.setTitle("Door Lock Simulation Page"); // Title of this
        doorFrame.setLayout(new GridLayout(3,1)); // Set border layout
        doorFrame.setResizable(false); // Set resizable to false
        doorFrame.setVisible(true); // Set visibility
        doorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Door Status Panel */
        JPanel statusPanel = new JPanel();
        doorStatusLabel = new JLabel("Door Status: ");
        statusPanel.add(doorStatusLabel);

        /* Authentication Panel */
        JPanel authPanel = new JPanel();
        authPanel.setLayout(new GridLayout(1, 3));
        authPanel.add(rfidScanner.getRFIDScanPanel());
        authPanel.add(faceScanner.getFaceScanPanel());
        authPanel.add(fingerprintScanner.getFingerprintScanPanel());
        authPanel.setFocusable(true);
        authPanel.addKeyListener(this);

        /* Exit Button */
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        /* Add to frame and pack */
        doorFrame.add(statusPanel);
        doorFrame.add(authPanel);
        doorFrame.add(exitButton);
        doorFrame.pack();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == exitButton){
            doorFrame.dispose();
            Main main = new Main(usersDB);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()){
            case 'r':
                // Simulate authorized RFID input
                if(!authSystem.getRfidPassed()){
                    rfidScanner.setRFID("rfid");
                    if(authSystem.verifyRFID(rfidScanner.scanRFID())){
                        rfidScanner.getRFIDScanPanel().setBackground(Color.GREEN);
                        faceScanner.start();
                        faceScanner.setStart(true);
                        faceScanner.getFaceScanPanel().setBackground(Color.WHITE);
                        fingerprintScanner.start();
                        fingerprintScanner.setStart(true);
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.WHITE);
                        authSystem.setRfidPassed(true);
                    }
                }
                break;
            case 'e':
                // Simulate unauthorized rfid input
                if(!authSystem.getRfidPassed()){
                    rfidScanner.setRFID("notrfid");
                    if(authSystem.verifyRFID(rfidScanner.scanRFID())){
                        rfidScanner.getRFIDScanPanel().setBackground(Color.GREEN);
                        faceScanner.start();
                        faceScanner.setStart(true);
                        faceScanner.getFaceScanPanel().setBackground(Color.WHITE);
                        fingerprintScanner.start();
                        fingerprintScanner.setStart(true);
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.WHITE);
                        authSystem.setRfidPassed(true);
                    }
                }
            case 'f':
                // Simulate authorized face input
                if(faceScanner.getStart()){
                    faceScanner.setFace("face");
                    if(authSystem.verifyFace(faceScanner.scanFace())){
                        faceScanner.getFaceScanPanel().setBackground(Color.GREEN);
                        faceScanner.setStart(false);
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.GRAY);
                        fingerprintScanner.setStart(false);
                        authSystem.unlockDoor();
                    }
                }
                break;
            case 'd':
                // Simulate unauthorized face input
                if(faceScanner.getStart()){
                    faceScanner.setFace("notface");
                    if(authSystem.verifyFace(faceScanner.scanFace())){
                        faceScanner.getFaceScanPanel().setBackground(Color.GREEN);
                        faceScanner.setStart(false);
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.GRAY);
                        fingerprintScanner.setStart(false);
                        authSystem.unlockDoor();
                    }
                }
                break;
            case 'p':
                // Simulate authorized fingerprint input
                if(fingerprintScanner.getStart()){
                    fingerprintScanner.setFinger("finger");
                    if(authSystem.verifyFinger(fingerprintScanner.scanFingerprint())){
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.GREEN);
                        fingerprintScanner.setStart(false);
                        faceScanner.getFaceScanPanel().setBackground(Color.GRAY);
                        faceScanner.setStart(false);
                        authSystem.unlockDoor();
                    }
                }
                break;
            case 'o':
                // Simulate unauthorized fingerprint input
                if(fingerprintScanner.getStart()){
                    fingerprintScanner.setFinger("notfinger");
                    if(authSystem.verifyFinger(fingerprintScanner.scanFingerprint())){
                        fingerprintScanner.getFingerprintScanPanel().setBackground(Color.GREEN);
                        fingerprintScanner.setStart(false);
                        faceScanner.getFaceScanPanel().setBackground(Color.GRAY);
                        faceScanner.setStart(false);
                        authSystem.unlockDoor();
                    }
                }
                break;
        }
        if(authSystem.getDeadlock()){
            fingerprintScanner.getFingerprintScanPanel().setBackground(Color.RED);
            faceScanner.getFaceScanPanel().setBackground(Color.RED);
            rfidScanner.getRFIDScanPanel().setBackground(Color.RED);
            authSystem.setRfidPassed(true);
            faceScanner.setStart(false);
            fingerprintScanner.setStart(false);
            System.out.println("Click exit to return to the main menu or wait for timeout.");
        }
    }
}
