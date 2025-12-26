package com.beautysalon.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String usernameAlreadyTaken) {
        super(usernameAlreadyTaken);
    }
}
