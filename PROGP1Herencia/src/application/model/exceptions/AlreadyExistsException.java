package application.model.exceptions;

/**
 * AlreadyExistsException: Error 007, thrown when trying to add
 * an already existing information to the model
 */
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException() {
        super("< ERROR 007: Ya existe un escuadron con ese nombre");
    }
}
