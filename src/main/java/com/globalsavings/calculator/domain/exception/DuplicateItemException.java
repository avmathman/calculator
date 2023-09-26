package com.globalsavings.calculator.domain.exception;

/**
 * Occurs when item with given data already exists.
 */
public class DuplicateItemException extends RuntimeException {
    
    /**
     * Initializes a new {@link DuplicateItemException} instance.
     *
     * @param message - error description message.
     */
    public DuplicateItemException(String message) {
        super(message);
    }

    /**
     * Initializes a new {@link DuplicateItemException} instance with the specified
     * description message and cause.
     *
     * @param message - error description message.
     * @param cause - the original cause of the exception.
     */
    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
