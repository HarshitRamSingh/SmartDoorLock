package org.example;

import org.example.display.AdminLoginPage;
import org.example.display.AdminMainPage;
import org.example.display.DoorSimulationPage;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    /* Navigation Buttons */
    JButton loginNavButton;
    JButton adminNavButton;
    JButton doorNavButton;
    /* User DB */
    UsersDB usersDB;
    /* Main Frame */
    JFrame mainFrame;

    public Main(UsersDB users) {

        /* Set User Database */
        usersDB = users;

        /* Main JFrame */
        mainFrame = new JFrame(); // Create frame
        mainFrame.setTitle("Smart Door Lock System"); // Title of mainFrame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame will exit when window is closed
        mainFrame.setLayout(new FlowLayout()); // Set flow layout
        mainFrame.setVisible(true); // Set visibility

        /* Navigation Buttons */

        // Login Nav Button will open the Login Simulation window
        loginNavButton = new JButton("Simulate Login");
        loginNavButton.addActionListener(this);

        // Admin Nav Button will open the Admin Page Simulation window
        adminNavButton = new JButton("Simulate Admin Page");
        adminNavButton.addActionListener(this);

        // Door Nav Button will open the Door Lock Simulation window
        doorNavButton = new JButton("Simulate Door Lock");
        doorNavButton.addActionListener(this);

        /* Add Buttons to Frame */
        mainFrame.add(loginNavButton);
        mainFrame.add(adminNavButton);
        mainFrame.add(doorNavButton);

        /* Pack Frame */
        mainFrame.pack();
    }

    public static void main(String[] args) {
        // Welcome
        System.out.println("Welcome. ");

        // Create new UserDB
        UsersDB users = new UsersDB();
        users.addUser(new User("Danielle",new RFID("rfid"), new Face("face"), new Fingerprint("0")));
        users.addUser(new User("Harshit",new RFID("rfid"), new Face("face"), new Fingerprint("finger")));

        // Create new main page using users
        Main mainPage = new Main(users);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == loginNavButton){
            System.out.println("Navigating to Login Simulation");
            mainFrame.dispose();
            AdminLoginPage adminLoginPage = new AdminLoginPage(usersDB);
        }
        else if(actionEvent.getSource() == adminNavButton){
            System.out.println("Navigating to Admin Page Simulation");
            mainFrame.dispose();
            AdminMainPage adminMainPage = new AdminMainPage(usersDB);
        }
        else if(actionEvent.getSource() == doorNavButton){
            System.out.println("Navigating to Door Lock Simulation");
            mainFrame.dispose();
            DoorSimulationPage doorSimulationPage = new DoorSimulationPage(usersDB);
        }
    }
}