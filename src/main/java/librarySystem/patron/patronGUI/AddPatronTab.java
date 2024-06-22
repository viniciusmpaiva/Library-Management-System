package librarySystem.patron.patronGUI;

import librarySystem.TabModel;
import librarySystem.patron.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Tab for adding manga
 * It has fields for the manga attributes and a button to add the manga
 * It implements the TabModel interface
 */
public class AddPatronTab implements TabModel {
    private final JFrame frame;
    private final PatronHandler handler;
    private final JTabbedPane tabbedPane;

    private JPanel addPanel;
    private JTextField addCpfField;
    private JTextField addNameField;
    private JTextField addLastNameField;
    private JTextField addPasswordField;
    private JTextField addEmailField;
    private JTextField addPhoneNumberField;
    private JButton addButton;

    /**
     * Constructor for the AddMangaTab
     * It initializes the frame, handler and tabbedPane
     * It creates the tab
     * @param frame the JFrame
     * @param handler the MangaHandler
     * @param tabbedPane the JTabbedPane
     */
    public AddPatronTab(JFrame frame, PatronHandler handler, JTabbedPane tabbedPane){
        this.frame = frame;
        this.handler = handler;
        this.tabbedPane = tabbedPane;

        createTab();
    }

    /**
     * Method to create the tab
     * It initializes the components and adds the components
     */
    public void createTab(){
        initComponents();
        addComponents();
    }

    /**
     * Method to initialize the components
     * It creates the fields for the manga attributes and the button
     */
    public void initComponents(){
        addPanel = new JPanel(new GridLayout(13, 2));
        addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addCpfField = new JTextField();
        addNameField = new JTextField();
        addLastNameField = new JTextField();
        addEmailField = new JTextField();
        addPasswordField = new JTextField();
        addPhoneNumberField = new JTextField();
        addButton = new JButton("Add Patron");
    }

    /**
     * Method to add the components to the tab
     * It adds the fields for the patron attributes and the button
     */
    public void addComponents(){
        addPanel.add(new JLabel("CPF:"));
        addPanel.add(addCpfField);
        addPanel.add(new JLabel("Name:"));
        addPanel.add(addNameField);
        addPanel.add(new JLabel("Last Name:"));
        addPanel.add(addLastNameField);
        addPanel.add(new JLabel("Email:"));
        addPanel.add(addEmailField);
        addPanel.add(new JLabel("Phone Number:"));
        addPanel.add(addPhoneNumberField);
        addPanel.add(new JLabel("Password:"));
        addPanel.add(addPasswordField);
        addButton.addActionListener(this);
        addPanel.add(new JLabel());
        addPanel.add(addButton);
        tabbedPane.addTab("Add Patron", addPanel);
    }

    /**
     * Method to add a Patron
     * It gets the values from the fields and creates a manga object
     * It adds the manga to the handler
     * It shows a message dialog with the result
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        try {
            Patron patron = new Patron(
                    addNameField.getText(),
                    addLastNameField.getText(),
                    addCpfField.getText(),
                    addPasswordField.getText(),
                    addEmailField.getText(),
                    addPhoneNumberField.getText()
            );
            handler.addPatron(patron);
            JOptionPane.showMessageDialog(frame, "Patron added successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error adding Patron." + "\n" + ex.getMessage());
        } catch(Exception ex){
            JOptionPane.showMessageDialog(frame, "Error adding Patron." + "\n" + "Please check the fields and try again.");
        }
    }

}
