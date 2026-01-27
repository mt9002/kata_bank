package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class InvalidInitialAmountException extends ApiException{
    public InvalidInitialAmountException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
