package org.helpers.common.api.exceptions;

import java.text.MessageFormat;

/**
 * Base class for service or business-logic exceptions.
 */
public class ServiceException extends RuntimeException {

    ServiceException(String message) {
        super(message);
    }

    public static ServiceException accessDenied(String message, Object... args) {
        return new AccessDeniedException(format(message, args));
    }

    public static ServiceException badRequest(String message, Object... args) {
        return new BadRequestException(format(message, args));
    }

    public static ServiceException notFound(String message, Object... args) {
        return new NotFoundException(format(message, args));
    }


    public static ServiceException notImplemented(String message, Object... args) {
        return new NotImplementedException(format(message, args));
    }


    public static ServiceException rateLimitExceeded(String message, Object... args) {
        return new RateLimitExceededException(format(message, args));
    }

    public static ServiceException unexpected(String message, Object... args) {
        return new UnexpectedException(format(message, args));
    }

    private static String format(String message, Object... args) {
        return MessageFormat.format(message, args);
    }
}
