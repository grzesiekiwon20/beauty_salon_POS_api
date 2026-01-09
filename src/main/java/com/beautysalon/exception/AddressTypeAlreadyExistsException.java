package com.beautysalon.exception;

public class AddressTypeAlreadyExistsException extends RuntimeException {
    public AddressTypeAlreadyExistsException(String message) {
        super(message);
    }
    public AddressTypeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
