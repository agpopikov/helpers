package org.helpers.common.api.exceptions;

/**
 * Specifies that method thrown this exception can't return
 * any specific data in current context (this can be local call or REST with auth context)
 * because callers side doesn't have rights to work with this method.
 * <p>
 * Corresponds to HTTP 403 code.
 */
public class AccessDeniedException extends ServiceException {

    AccessDeniedException(String message) {
        super(message);
    }
}
