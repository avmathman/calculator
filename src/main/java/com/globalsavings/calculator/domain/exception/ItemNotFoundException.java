package com.globalsavings.calculator.domain.exception;

/**
 * Occurs when requested item can not be found.
 */
public class ItemNotFoundException extends RuntimeException {

    /**
     * Initializes a new {@link ItemNotFoundException} instance.
     *
     * @param message - error description message.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
