package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class InvalidTransactionAmountException extends ApiException{
    public InvalidTransactionAmountException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
