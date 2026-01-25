package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class InvalidNumAccountException extends ApiException{
    public InvalidNumAccountException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
