package librarySystem;

import javax.swing.*;
import java.awt.*;

import librarySystem.book.bookGUI.BookFrame;
import librarySystem.patron.patronGUI.PatronFrame;

/**
 * Frame for the admin to manage the library system
 */
public class LibraryFrame {
    private final JFrame frame;
    private JPanel panel;

    private JLabel titleLabel;
    private JButton bookButton;
    private JButton patronButton;

    /**
     * Constructor for the AdminFrame
     * It initializes the frame and creates the components
     */
    public LibraryFrame() {
        frame = new JFrame("Library System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Font font = new Font("Arial", Font.BOLD, 20);
        Color backgroundColor = new Color(230, 230, 250); // Light lavender
        Color foregroundColor = new Color(25, 25, 112); // Midnight blue

        // Modify the look and feel of the GUI
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.foreground", foregroundColor);
        UIManager.put("Button.foreground", foregroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("Button.background", Color.LIGHT_GRAY);

        createComponents();
        addComponents();

        frame.setVisible(true);
    }

    /**
     * Method to create the components
     * It initializes the panel, titleLabel, bookButton, patronButton and loginButton
     */
    private void createComponents(){
        panel = new JPanel(new GridLayout(3, 1));
        titleLabel = new JLabel("Choose one of the following options to manage:");
        titleLabel.setBounds(350, 50, 100, 50);

        bookButton = new JButton("Manage Books");
        bookButton.setBounds(300, 200, 200, 50);
        bookButton.addActionListener(e -> {
            new BookFrame();
            frame.dispose();
        });

        patronButton = new JButton("Manage Patrons");
        patronButton.setBounds(300, 300, 200, 50);
        patronButton.addActionListener(e -> {
            new PatronFrame();
            frame.dispose();
        });

    }

    /**
     * Method to add the components to the panel and the panel to the frame
     */
    private void addComponents(){
        panel.add(titleLabel);
        panel.add(bookButton);
        panel.add(patronButton);
        frame.add(panel);
    }
}
