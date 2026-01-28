package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentAccountException extends ApiException{
    public IllegalArgumentAccountException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
