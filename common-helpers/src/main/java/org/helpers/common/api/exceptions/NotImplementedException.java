package org.helpers.common.api.exceptions;

/**
 * Specifies that method thrown this exception is not implemented
 * or partially implemented at this time, e.g. implemented only for specific cases.
 * <p>
 * Corresponds to HTTP 501 code.
 */
public class NotImplementedException extends ServiceException {

    public NotImplementedException(String message) {
        super(message);
    }
}
