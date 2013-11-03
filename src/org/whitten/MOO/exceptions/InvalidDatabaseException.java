package org.whitten.MOO.exceptions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class InvalidDatabaseException extends Exception {
    public InvalidDatabaseException() {
        super();
    }

    public InvalidDatabaseException(String message) {
        super(message);
    }

    public InvalidDatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidDatabaseException(Throwable throwable) {
        super(throwable);
    }
    
}
