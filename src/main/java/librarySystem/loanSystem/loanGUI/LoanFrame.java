package librarySystem.loanSystem.loanGUI;

import librarySystem.book.bookGUI.CloseLoanTab;
import librarySystem.book.bookGUI.NewLoanTab;
import librarySystem.loanSystem.LoanSystemFileHandler;
import librarySystem.patron.PatronHandler;

import javax.swing.*;
import java.awt.*;


public class LoanFrame {
    private final LoanSystemFileHandler loanManager;
    private final JTabbedPane tabbedPane;
    private final JFrame frame;

    /**
     * Constructor for the MangaHandlerGUI
     * It creates a JFrame with a JTabbedPane
     * The JTabbedPane has tabs for adding, updating, removing, searching and viewing book
     */
    public LoanFrame() {
        loanManager = new LoanSystemFileHandler();
        frame = new JFrame("Loan Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Font font = new Font("Arial", Font.BOLD, 16);
        Color backgroundColor = new Color(230, 230, 250); // Light lavender
        Color foregroundColor = new Color(25, 25, 112); // Midnight blue

        // Modify the look and feel of the GUI
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Label.foreground", foregroundColor);
        UIManager.put("TextField.foreground", foregroundColor);
        UIManager.put("Button.foreground", foregroundColor);
        UIManager.put("TextArea.foreground", foregroundColor);
        UIManager.put("TabbedPane.foreground", foregroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextArea.background", Color.WHITE);
        UIManager.put("Button.background", Color.LIGHT_GRAY);

        tabbedPane = new JTabbedPane();


        // Tab for adding patron
        //Tab for viewing patron
        new NewLoanTab(frame,loanManager,tabbedPane);
        new SearchLoanTab(frame,loanManager,tabbedPane);
        new CloseLoanTab(frame,loanManager,tabbedPane);

        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

}

