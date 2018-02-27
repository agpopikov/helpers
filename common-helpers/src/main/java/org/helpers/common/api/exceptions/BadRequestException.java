package org.helpers.common.api.exceptions;

/**
 * Specifies that method thrown this exception is received invalid
 * input data and can't be completed successfully, e.g. any validation issues.
 * <p>
 * Corresponds to HTTP 400 code.
 */
public class BadRequestException extends ServiceException {

    BadRequestException(String message) {
        super(message);
    }
}
