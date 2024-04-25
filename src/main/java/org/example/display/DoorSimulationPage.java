package org.example.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.example.Main;
import org.example.model.RFID;
import org.example.model.User;
import org.example.model.UsersDB;
import org.example.service.AuthenticationSystem;
import org.example.service.FaceAuthentication;
import org.example.service.FingerprintAuthentication;
import org.example.service.RFIDAuthentication;

public class DoorSimulationPage extends JFrame implements ActionListener {
    UsersDB usersDB;
    JButton exitButton;
    JFrame doorFrame;
    JButton fingerButton;
    JLabel doorStatusLabel;
    AuthenticationSystem authSystem;

    public DoorSimulationPage(UsersDB users){

        /* Database and Auth */
        usersDB = users;
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

        /* Exit Button */
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        fingerButton = new JButton("Scan Finger");
        fingerButton.addActionListener(this);


        /* Add to frame and pack */
        doorFrame.add(doorStatusLabel);
        doorFrame.add(exitButton);
        doorFrame.add(fingerButton);
        doorFrame.pack();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == exitButton){
            doorFrame.dispose();
            Main main = new Main(usersDB);
        }
        if(actionEvent.getSource() == fingerButton){
            authSystem.validateUser();
        }
    }
}
