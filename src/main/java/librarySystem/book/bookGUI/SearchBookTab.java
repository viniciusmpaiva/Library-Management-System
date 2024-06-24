package librarySystem.book.bookGUI;

import librarySystem.TabModel;
import librarySystem.book.Book;
import librarySystem.book.BookHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

/**
 * Tab for searching book
 * It has fields for the book attributes and buttons to search the book
 * It implements the TabModel interface
 */
public class SearchBookTab implements TabModel {
    private final JFrame frame;
    private final BookHandler handler;
    private final JTabbedPane tabbedPane;

    private JPanel searchPanel;

    private JTextField searchTitleField;
    private JTextField searchIsbnField;
    private JTextField searchAuthorField;
    private JTextField searchGenreField;

    private JButton searchByTitleButton;
    private JButton searchByIsbnButton;
    private JButton searchByAuthorButton;
    private JButton searchByGenreButton;

    private JTextArea searchResultsArea;

    private JScrollPane scrollPane;
    private JPanel searchInputPanel;
    private JPanel searchButtonPanel;

    /**
     * Constructor for the SearchbookTab
     * It initializes the frame, handler and tabbedPane
     * It creates the tab
     * @param frame the JFrame
     * @param handler the bookHandler
     * @param tabbedPane the JTabbedPane
     */
    public SearchBookTab(JFrame frame, BookHandler handler, JTabbedPane tabbedPane){
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

        searchTitleField = new JTextField();
        searchIsbnField = new JTextField();
        searchAuthorField = new JTextField();
        searchGenreField = new JTextField();

        searchByTitleButton = new JButton("Search by Title");
        searchByIsbnButton = new JButton("Search by ISBN");
        searchByAuthorButton = new JButton("Search by Author");
        searchByGenreButton = new JButton("Search by Genre");
        
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
        searchInputPanel.add(new JLabel("Search by Title:"));
        searchInputPanel.add(searchTitleField);
        searchInputPanel.add(new JLabel("Search by ISBN:"));
        searchInputPanel.add(searchIsbnField);
        searchInputPanel.add(new JLabel("Search by Author:"));
        searchInputPanel.add(searchAuthorField);
        searchInputPanel.add(new JLabel("Search by Genre:"));
        searchInputPanel.add(searchGenreField);

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        searchButtonPanel.add(searchByTitleButton);
        searchButtonPanel.add(searchByIsbnButton);
        searchButtonPanel.add(searchByAuthorButton);
        searchButtonPanel.add(searchByGenreButton);
        searchPanel.add(searchButtonPanel, BorderLayout.SOUTH);

        searchByTitleButton.addActionListener(this);
        searchByIsbnButton.addActionListener(this);
        searchByAuthorButton.addActionListener(this);
        searchByGenreButton.addActionListener(this);
        tabbedPane.addTab("Search Book", searchPanel);
    }

    /**
     * Method to handle the action events
     * It searches the book by title or isbn
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        List<Book> books;
        if(e.getSource() == searchByTitleButton){
            try {
                books = handler.searchBooksByTitle(searchTitleField.getText());
                searchResultsArea.setText("");
                for (Book book : books) {
                    searchResultsArea.append(book.toString() + "\n");
                }
                if (books.isEmpty()) {
                    searchResultsArea.append("No books found.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching book.");
            }
        } else if(e.getSource() == searchByIsbnButton){
            try {
                books = handler.searchBookByIsbn(searchIsbnField.getText());
                searchResultsArea.setText("");
                for (Book book : books) {
                    searchResultsArea.append(book.toString() + "\n");
                }
                if (books.isEmpty()) {
                    searchResultsArea.append("No books found.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching book.");
            }
        }else if(e.getSource() == searchByAuthorButton){
            try {
                books = handler.searchBooksByAuthor(searchAuthorField.getText());
                searchResultsArea.setText("");
                for (Book book : books) {
                    searchResultsArea.append(book.toString() + "\n");
                }
                if (books.isEmpty()) {
                    searchResultsArea.append("No books found.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching book.");
            }
        }else if(e.getSource() == searchByGenreButton){	
            try {
                books = handler.searchBooksByGenre(searchGenreField.getText());
                searchResultsArea.setText("");
                for (Book book : books) {
                    searchResultsArea.append(book.toString() + "\n");
                }
                if (books.isEmpty()) {
                    searchResultsArea.append("No books found.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching book.");
            }
        }
    }
}
