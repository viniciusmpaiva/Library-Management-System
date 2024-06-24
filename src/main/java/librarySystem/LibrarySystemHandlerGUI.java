package librarySystem;

import librarySystem.login.Login;
import librarySystem.login.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to handle the GUI for the Library System
 * It has a JFrame with a login form
 * It has fields for username and password and a login button
 * It implements the ActionListener interface
 */
public class LibrarySystemHandlerGUI implements ActionListener {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginHandler loginHandler;

    /**
     * Constructor for the LibrarySystemHandlerGUI
     * It initializes the loginHandler and creates and adds components
     */
    public LibrarySystemHandlerGUI() {
        loginHandler = new LoginHandler();
        createAndAddComponents();
    }

    /**
     * Method to create and add components to the frame
     * It initializes the frame and components
     * It adds the components to the frame
     */
    private void createAndAddComponents() {
        frame = new JFrame("Login System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 300);

        // Define a GridLayout with 3 rows and 2 columns
        frame.setLayout(new GridLayout(4, 2, 10, 10));
        JLabel greetingLabel = new JLabel("Welcome to the Library Management System !!!");
        JLabel titleLabel = new JLabel("Please enter your credentials to login:");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        
        // Add components to the frame
        frame.add(greetingLabel);
        frame.add(titleLabel);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(new JLabel()); // empty label to adjust the layout
        frame.add(loginButton);

        frame.setVisible(true);
    }
    
    /**
     * Method to handle the login button click
     * It gets the username and password from the fields
     * It creates a Login object and checks if the login is valid
     * If the login is valid, it opens the LibraryFrame
     * If the login is invalid, it shows an error message
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Login login = new Login(username, password);

        if (loginHandler.isAdminLogin(login)) {
            JOptionPane.showMessageDialog(frame, "Admin login successful!");
            frame.dispose();
            new AdminFrame();
            return;
        } else if (loginHandler.isValidLogin(login)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            frame.dispose();
            new LibraryFrame();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.");
        }
    }

}

