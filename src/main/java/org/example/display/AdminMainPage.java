package org.example.display;

import org.example.Main;
import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AdminMainPage extends JFrame implements ActionListener {
    JButton addUserButton;
    JButton removeUserButton;
    JButton searchUserButton;
    JButton viewAllUsersButton;
    JButton exitButton;
    JTextField userIdField;
    UsersDB usersDB;
    JFrame adminMainFrame;

    public AdminMainPage(UsersDB users){

        /* UsersDB Setup */
        usersDB = users;

        /* JFrame Setup */
        adminMainFrame = new JFrame(); // Create frame
        adminMainFrame.setTitle("Admin Main Page"); // Title of this
        adminMainFrame.setLayout(new GridLayout(4,1)); // Set border layout
        adminMainFrame.setResizable(false); // Set resizable to false
        adminMainFrame.setVisible(true); // Set visibility
        adminMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* UserId Panel */
        JPanel userIdPanel = new JPanel();
        userIdPanel.setLayout(new FlowLayout());
        JLabel userIdLabel = new JLabel("Enter User Id: ");
        userIdField = new JTextField();
        userIdField.setPreferredSize(new Dimension(250,40));
        userIdPanel.add(userIdLabel);
        userIdPanel.add(userIdField);

        /* Button Panel */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        addUserButton = new JButton("Add");
        addUserButton.setPreferredSize(new Dimension(100, 40));
        addUserButton.addActionListener(this);
        searchUserButton = new JButton("Search");
        searchUserButton.setPreferredSize(new Dimension(100, 40));
        searchUserButton.addActionListener(this);
        removeUserButton = new JButton("Remove");
        removeUserButton.setPreferredSize(new Dimension(100,40));
        removeUserButton.addActionListener(this);
        buttonPanel.add(addUserButton);
        buttonPanel.add(searchUserButton);
        buttonPanel.add(removeUserButton);

        /* View Users Panel */
        JPanel viewAllUsersPanel = new JPanel();
        viewAllUsersButton = new JButton("View All Users");
        viewAllUsersButton.addActionListener(this);
        viewAllUsersPanel.add(viewAllUsersButton);

        /* Exit Panel */
        JPanel exitPanel = new JPanel();
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitPanel.add(exitButton);

        /* Add Panels to Admin Main Frame */
        adminMainFrame.add(userIdPanel);
        adminMainFrame.add(buttonPanel);
        adminMainFrame.add(viewAllUsersPanel);
        adminMainFrame.add(exitPanel);

        /* Pack */
        adminMainFrame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String userId = userIdField.getText();
        if(actionEvent.getSource() == addUserButton){
            System.out.println("Attempting to add user " + userId + "...");
            if(!usersDB.isUserExist(userId)){
                System.out.println("Generating data...");
                Random rand = new Random();
                String rfid = rand.nextInt(1000) + "";
                String face = rand.nextInt(1000) + "";
                String fingerprint = rand.nextInt(1000) + "";
                User newUser = new User(userId, new RFID(rfid), new Face(face), new Fingerprint(fingerprint));
                usersDB.addUser(newUser);
                System.out.println("User " + userId + " added successfully. \n");
            }
            else {
                System.out.println("User " + userId + " already exists.");
            }
        }
        if(actionEvent.getSource() == searchUserButton){
            System.out.println("Searching for user " + userId + "...");
            if(!usersDB.isUserExist(userId)){
                System.out.println("User " + userId + " does not exist.");
            }
            else {
                User foundUser = usersDB.getUser(userId);
                System.out.println("UserId: " + userId);
                System.out.println("Fingerprint: " + foundUser.getFingerPrintData());
                System.out.println("Face: " + foundUser.getFaceData());
                System.out.println("RFID: " + foundUser.getRfidData());
                System.out.println("Successfully found. \n");
            }
        }
        if(actionEvent.getSource() == removeUserButton){
            System.out.println("Attempting to remove user " + userId + "...");
            if(!usersDB.isUserExist(userId)){
                System.out.println("User " + userId + " does not exist.");
            } else {
                User removable = usersDB.getUser(userId);
                usersDB.removeUser(removable);
                System.out.println("User " + userId + " successfully removed. \n");
            }
        }
        if(actionEvent.getSource() == viewAllUsersButton) {
            System.out.println("All current user ids: ");
            for(User user : usersDB.getUsers()){
                System.out.println(user.getUserID());
            }
            System.out.println();
        }
        if(actionEvent.getSource() == exitButton){
            adminMainFrame.dispose();
            Main main = new Main(usersDB);
        }
    }
}
