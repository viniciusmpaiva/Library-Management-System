package librarySystem.loanSystem.loanGUI;
import librarySystem.TabModel;
import librarySystem.book.Book;
import librarySystem.book.BookHandler;
import librarySystem.loanSystem.LoanSystemFileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

/**
 * Tab for searching manga
 * It has fields for the manga attributes and buttons to search the manga
 * It implements the TabModel interface
 */
public class SearchLoanTab implements TabModel {
    private final JFrame frame;
    private final LoanSystemFileHandler handler;
    private final JTabbedPane tabbedPane;

    private JPanel searchPanel;

    private JTextField searchIsbnField;

    private JButton searchByIsbnButton;
    private JTextArea searchResultsArea;

    private JScrollPane scrollPane;
    private JPanel searchInputPanel;
    private JPanel searchButtonPanel;


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
    public void createTab() {
        initComponents();
        addComponents();
    }

    /**
     * Method to initialize the components
     */
    public void initComponents() {
        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        searchIsbnField = new JTextField();
        searchByIsbnButton = new JButton("Search by ISBN");

        searchResultsArea = new JTextArea();
        searchResultsArea.setEditable(false);
        scrollPane = new JScrollPane(searchResultsArea);
        searchInputPanel = new JPanel(new GridLayout(2, 2));
        searchButtonPanel = new JPanel(new GridLayout(1, 2));
    }

    /**
     * Method to add the components
     */
    public void addComponents() {
        searchInputPanel.add(new JLabel("Search by ISBN:"));
        searchInputPanel.add(searchIsbnField);

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        searchButtonPanel.add(searchByIsbnButton);
        searchPanel.add(searchButtonPanel, BorderLayout.SOUTH);

        searchByIsbnButton.addActionListener(this);
        tabbedPane.addTab("Check Loan", searchPanel);
    }

    /**
     * Method to handle the action events
     * It searches the manga by title or isbn
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchByIsbnButton){
            try {
                String loan = handler.checkLoan(searchIsbnField.getText());
                searchResultsArea.setText("");
                searchResultsArea.append(loan);
                if(handler.isLate(searchIsbnField.getText())){
                    JOptionPane.showMessageDialog(frame, "This loan is late. You need to pay a 20$ worth fine");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching book.");
            }
        }
    }
}
