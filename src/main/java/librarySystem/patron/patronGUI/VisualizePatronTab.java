package librarySystem.patron.patronGUI;

import librarySystem.TabModel;
import librarySystem.patron.PatronHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;


/**
 * Tab for visualizing patrons
 * It has a button to refresh the list of patrons
 * It implements the TabModel interface
 */
public class VisualizePatronTab implements TabModel {
    private final JFrame frame;
    private final PatronHandler handler;
    private final JTabbedPane tabbedPane;

    private JPanel viewPanel;
    private JTextArea viewResultsArea;
    JScrollPane viewScrollPane;
    JButton refreshButton;

    /**
     * Constructor for the VisualizePatronTab
     * It initializes the frame, handler and tabbedPane
     * It creates the tab
     * @param frame the JFrame
     * @param handler the PatronHandler
     * @param tabbedPane the JTabbedPane
     */
    public VisualizePatronTab(JFrame frame, PatronHandler handler, JTabbedPane tabbedPane) {
        this.frame = frame;
        this.handler = handler;
        this.tabbedPane = tabbedPane;

        createTab();
    }

    /**
     * Method to create the tab
     * It initializes the components and adds the components
     */
    public void createTab() {
        initComponents();
        addComponents();
    }

    /**
     * Method to initialize the components
     * It initializes the panel, text area, scroll pane and button
     */
    public void initComponents() {
        viewPanel = new JPanel(new BorderLayout());
        viewResultsArea = new JTextArea();
        viewResultsArea.setEditable(false);
        viewScrollPane = new JScrollPane(viewResultsArea);
        refreshButton = new JButton("Refresh List");
    }

    /**
     * Method to add the components to the panel
     */
    public void addComponents() {
        refreshButton.addActionListener(this);
        viewPanel.add(viewScrollPane, BorderLayout.CENTER);
        viewPanel.add(refreshButton, BorderLayout.SOUTH);
        tabbedPane.addTab("View All Patrons", viewPanel);
    }

    /**
     * Method to refresh the list of patrons
     * It gets the list of patrons from the handler and displays it in the text area
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        try {
            List<String> mangaTitles = handler.getAllPatronsLastNames();
            viewResultsArea.setText("");
            for (String title : mangaTitles) {
                viewResultsArea.append(title + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading patrons titles.");
        }
    }
}
