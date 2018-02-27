package org.helpers.common.api.exceptions;

/**
 * Specifies that method thrown this exception is contains any
 * unhandled exception and / or doesn't work properly.
 * <p>
 * Corresponds to HTTP 500 code.
 */
public class UnexpectedException extends ServiceException {

    public UnexpectedException(String message) {
        super(message);
    }
}
