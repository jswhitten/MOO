package org.whitten.MOO.exceptions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class VerbNotFoundException extends Exception {
    public VerbNotFoundException() {
        super();
    }

    public VerbNotFoundException(String message) {
        super(message);
    }

    public VerbNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public VerbNotFoundException(Throwable throwable) {
        super(throwable);
    }
    
}
