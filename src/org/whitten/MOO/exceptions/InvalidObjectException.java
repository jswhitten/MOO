package org.whitten.MOO.exceptions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class InvalidObjectException extends Exception {
    public InvalidObjectException() {
        super();
    }

    public InvalidObjectException(String message) {
        super(message);
    }

    public InvalidObjectException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidObjectException(Throwable throwable) {
        super(throwable);
    }
    
}
