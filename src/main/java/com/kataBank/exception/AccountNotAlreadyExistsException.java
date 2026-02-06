package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class AccountNotAlreadyExistsException extends ApiException{
    public AccountNotAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
