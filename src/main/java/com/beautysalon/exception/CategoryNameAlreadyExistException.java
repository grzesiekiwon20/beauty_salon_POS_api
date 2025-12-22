package com.beautysalon.exception;


public class CategoryNameAlreadyExistException extends RuntimeException {

    public CategoryNameAlreadyExistException(String message) {
        super(message);
    }
}