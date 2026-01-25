package com.kataBank.exception;

import org.springframework.http.HttpStatus;

public class InvalidDepositException extends ApiException{
    public InvalidDepositException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }
}
