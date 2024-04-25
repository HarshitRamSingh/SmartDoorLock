package org.example.display;

import org.example.Main;
import org.example.model.UsersDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPage extends JFrame implements ActionListener {

    JTextField usernameField;
    JTextField passwordField;
    JButton loginButton;
    JButton exitButton;
    String username;
    String password;
    UsersDB users;
    JFrame loginFrame;


    public AdminLoginPage(UsersDB usersDB){

        /* Set UserDB */
        users = usersDB;

        /* JFrame Setup */
        loginFrame = new JFrame();
        loginFrame.setTitle("Admin Login"); // Title of this
        loginFrame.setLayout(new FlowLayout()); // Set border layout
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Username Text Field */
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(250,40));
        loginFrame.add(usernameField);

        /* Password Text Field */
        passwordField = new JTextField();
        passwordField.setPreferredSize(new Dimension(250, 40));
        loginFrame.add(passwordField);

        /* Login Button */
        loginButton = new JButton("Login");
        loginFrame.add(loginButton);
        loginButton.addActionListener(this);

        /* Exit Button */
        exitButton = new JButton("Exit");
        loginFrame.add(exitButton);
        exitButton.addActionListener(this);

        /* Pack and Set Visibility */
        loginFrame.pack(); // Frame scales to fit contents
        loginFrame.setVisible(true); // Set visibility
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == loginButton){
            username = usernameField.getText();
            password = passwordField.getText();
        }
        if(actionEvent.getSource() == exitButton){
            loginFrame.dispose();
            Main main = new Main(users);
        }
    }
}
