package application.model.exceptions;

/**
 * DoesntExistException: ERROR 005, thrown when trying to
 * upgrade some properties of a squad
 */
public class DoesntExistException extends Exception {
    public DoesntExistException() {
        super("< ERROR 005: No existe un escuadron con ese nombre >");
    }
}
