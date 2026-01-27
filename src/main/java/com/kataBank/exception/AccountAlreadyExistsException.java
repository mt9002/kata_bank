package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends ApiException{
    public AccountAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}