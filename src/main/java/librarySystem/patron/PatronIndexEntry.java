package librarySystem.patron;

/**
 * Class to represent an entry in the patron index
 * It has a CPF and a file pointer
 * The file pointer is the position of the patron in the patron file
 */
public class PatronIndexEntry {
    private final  String cpf;
    private final  long filePointer;

    /**
     * Constructor
     * @param cpf ISBN
     * @param filePointer File pointer
     */
    public PatronIndexEntry(String cpf, long filePointer) {
        this.cpf = cpf;
        this.filePointer = filePointer;
    }

    /**
     * Get the CPF
     * @return CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Get the file pointer
     * @return File pointer
     */
    public long getFilePointer() {
        return filePointer;
    }
}
