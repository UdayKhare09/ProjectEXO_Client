package dev.uday.GUI;

import dev.uday.NET.SocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel {
    public static JPanel loginPanel;
    static JTextField usernameField;
    static JPasswordField passwordField;
    static JButton loginButton;

    public static void setLoginPanel() {
        loginPanel = new JPanel();

        // Set Layout with padding
        GridBagLayout layout = new GridBagLayout();

        // Add Login Components
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField(20);
        usernameField.addActionListener(ActionListener -> {
            ;
            // focus password field on Enter
            passwordField.requestFocus();
        });

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(20);
        passwordField.addActionListener((ActionEvent e) -> {
            login();
        });
        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            login();
        });

        // Add Components to Panel
        loginPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);
    }

    private static void login() {
        // Login Logic
        Thread loginThread = new Thread(() -> {
            SocketClient.init(usernameField.getText(), passwordField.getText());
        });
        loginThread.start();
    }
}
