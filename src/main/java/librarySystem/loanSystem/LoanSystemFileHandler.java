package librarySystem.loanSystem;

import librarySystem.book.Book;
import librarySystem.patron.Patron;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

/**
 * Loan System File Handler Class: It represents
 * a loan system file handler object with its attributes
 * - filename: Name of the file
 * - isAvailable: Method to check if a book is available
 * - writeNewLoan: Method to write a new loan
 */
public class LoanSystemFileHandler {
    private String DATA_LOAN_FILE = "data_loan.dat";
    private String INDEX_LOAN_FILE = "index_loan.dat";


    public boolean isBookAvailable(Book book) throws IOException {
        try (RandomAccessFile index_file = new RandomAccessFile(INDEX_LOAN_FILE, "rw")) {
            while (index_file.getFilePointer() < index_file.length()) {
                String tempIsbn = index_file.readUTF();
                String status = index_file.readUTF();
                index_file.readLong();
                if (tempIsbn.equals(book.getIsbn()) && status.equals("OPEN")){
                    return false;
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating file...");
            return true;
        }
    }

    public void newLoan(LoanSystem loan) throws IOException {
        try(RandomAccessFile file = new RandomAccessFile(DATA_LOAN_FILE, "rw")) {
            file.seek(file.length());
            writeIndex(loan.getLoanedIsbn(), file.getFilePointer());
            file.writeUTF(loan.getLoanedIsbn());
            file.writeUTF(loan.getcpf());
            file.writeUTF(loan.getStartDate().toString());
            file.writeUTF(loan.getDueDate().toString());
            file.writeUTF(loan.getStatus());
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating file...");
        }
    }

    public void writeIndex(String isbn, long offset) throws IOException {
        try(RandomAccessFile file = new RandomAccessFile(INDEX_LOAN_FILE, "rw")) {
            file.seek(file.length());
            file.writeUTF(isbn);
            file.writeUTF("OPEN");
            file.writeLong(offset);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating file...");
        }
    }

    public void closeLoan(String isbn) throws IOException {
        try(RandomAccessFile indexFile = new RandomAccessFile(INDEX_LOAN_FILE, "rw")) {
            while(indexFile.getFilePointer() < indexFile.length()){
                long filePosition = indexFile.getFilePointer();
                String tempIsbn = indexFile.readUTF();
                String status = indexFile.readUTF();
                if(tempIsbn.equals(isbn) && status.equals("OPEN")){
                    long offset = indexFile.readLong();
                    try(RandomAccessFile dataFile = new RandomAccessFile(DATA_LOAN_FILE, "rw")) {
                        dataFile.seek(offset);
                        dataFile.readUTF();
                        dataFile.readUTF();
                        dataFile.readUTF();
                        dataFile.readUTF();
                        dataFile.writeUTF("CLOSED");
                        return;
                    }
                }
                indexFile.readLong();
            }
            throw new IOException("Loan not found");
        }
    }

    public String checkLoan(String isbn) throws IOException{
        try(RandomAccessFile indexFile = new RandomAccessFile(INDEX_LOAN_FILE,"rw")){
            while(indexFile.getFilePointer() < indexFile.length()){
                String tempisbn = indexFile.readUTF();
                String status = indexFile.readUTF();
                long offset = indexFile.readLong();
                if(status.equals("OPEN") && tempisbn.equals(isbn)){
                    try(RandomAccessFile dataFile = new RandomAccessFile(DATA_LOAN_FILE,"rw")){
                        dataFile.seek(offset);
                        String loanedIsbn = dataFile.readUTF();
                        String cpf = dataFile.readUTF();
                        LocalDate startDate = LocalDate.parse(dataFile.readUTF());
                        LocalDate dueDate = LocalDate.parse(dataFile.readUTF());
                        String statusLoan = dataFile.readUTF();
                        return "ISBN: " + loanedIsbn +
                                "\nCPF: " + cpf+
                                "\nStart Date: " + startDate+
                                "\nDue Date: " + dueDate +
                                "\nStatus: " + statusLoan;
                    }
                }
            }
            throw new IOException("Loan not found");
        }
    }


}


