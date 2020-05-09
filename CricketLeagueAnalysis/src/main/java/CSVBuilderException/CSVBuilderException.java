package CSVBuilderException;

public class CSVBuilderException extends RuntimeException {

    public ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super( message );
        this.type = type;
    }
    public enum ExceptionType {
        FILE_PROBLEM
    }
}
