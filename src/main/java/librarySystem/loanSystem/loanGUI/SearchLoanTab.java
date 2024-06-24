package librarySystem.book.bookGUI;

import librarySystem.TabModel;
import librarySystem.loanSystem.LoanSystemFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Tab for adding manga
 * It has fields for the manga attributes and a button to add the manga
 * It implements the TabModel interface
 */
public class SearchLoanTab implements TabModel {
    private final JFrame frame;
    private final LoanSystemFileHandler handler;
    private final JTabbedPane tabbedPane;

    private JPanel addPanel;
    private JTextField getIsbnField;
    private JButton addButton;

    /**
     * Constructor for the AddMangaTab
     * It initializes the frame, handler and tabbedPane
     * It creates the tab
     * @param frame the JFrame
     * @param handler the MangaHandler
     * @param tabbedPane the JTabbedPane
     */
    public SearchLoanTab(JFrame frame, LoanSystemFileHandler handler, JTabbedPane tabbedPane){
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
        getIsbnField = new JTextField();
        addButton = new JButton("Search Loan");
    }

    /**
     * Method to add the components to the tab
     * It adds the fields for the manga attributes and the button
     */
    public void addComponents(){
        addPanel.add(new JLabel("ISBN:"));
        addPanel.add(getIsbnField);

        addButton.addActionListener(this);
        addPanel.add(new JLabel());
        addPanel.add(addButton);
        tabbedPane.addTab("Search Loan", addPanel);
    }

    /**
     * Method to add a manga
     * It gets the values from the fields and creates a manga object
     * It adds the manga to the handler
     * It shows a message dialog with the result
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println(handler.checkLoan(getIsbnField.getText()));
            JOptionPane.showMessageDialog(frame, "Loan Found successfully!");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error adding book." + "\n" + ex.getMessage());
        } catch(Exception ex){
            JOptionPane.showMessageDialog(frame, "Error adding book." + "\n" + "Please check the fields and try again.");
        }
    }

}
