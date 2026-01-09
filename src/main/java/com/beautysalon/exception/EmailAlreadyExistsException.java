package com.beautysalon.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String emailAlreadyRegistered) {
        super(emailAlreadyRegistered);
    }
}
