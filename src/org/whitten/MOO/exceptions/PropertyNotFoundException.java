package org.whitten.MOO.exceptions;

/**
 *
 * @author Jed Whitten <jed@whitten.org>
 */
public class PropertyNotFoundException extends Exception {
    public PropertyNotFoundException() {
        super();
    }

    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public PropertyNotFoundException(Throwable throwable) {
        super(throwable);
    }
    
}
