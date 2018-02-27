package org.helpers.common.api.exceptions;

/**
 * Exception describes that target method call count limit exceeded
 * and caller side should respect this limitations.
 * <p>
 * Corresponds to HTTP 429 code.
 */
public class RateLimitExceededException extends ServiceException {

    RateLimitExceededException(String message) {
        super(message);
    }
}
