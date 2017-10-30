package application.model.exceptions;

/**
 * InvalidDataException: All exceptions related to wrong data input, like...
 *  - wrong number of arguments for an operation (ERROR 001)
 *  - wrong species name aka Protoss, Zerg or Terran (ERROR 002)
 *  - signed numbers when they should be unsigned (ERROR 003)
 *  - invalid operation (ERROR 004)
 *  - invalid property of a squad when trying to modify it (ERROR 006)
 */
public class InvalidDataException extends Exception {


    public InvalidDataException(String error) {
        super(error);
    }
}
