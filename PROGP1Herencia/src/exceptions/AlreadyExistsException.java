package exceptions;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {
        super("< ERROR 007: A squad with that name already exists");
    }
}
