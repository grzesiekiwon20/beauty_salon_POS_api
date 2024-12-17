package com.beautysalon.exception;

public class CartAlreadyExistsException extends RuntimeException {
    public CartAlreadyExistsException(String message) {
        super(message);
    }
    public CartAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
