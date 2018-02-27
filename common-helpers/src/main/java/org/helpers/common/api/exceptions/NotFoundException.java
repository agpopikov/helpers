package org.helpers.common.api.exceptions;

/**
 * Specifies that method thrown this exception couldn't
 * find any request specific entity or value.
 * <p>
 * Corresponds to HTTP 404 code.
 */
public class NotFoundException extends ServiceException {

    NotFoundException(String message) {
        super(message);
    }
}
