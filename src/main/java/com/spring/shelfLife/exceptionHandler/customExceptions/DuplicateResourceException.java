package com.spring.shelfLife.exceptionHandler.customExceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
