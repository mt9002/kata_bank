package com.kataBank.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handlerBusiness(ApiException ex){
        ProblemDetail problem = ProblemDetail.forStatus(ex.getHttpStatus());
        problem.setTitle("business mistake ");
        problem.setDetail(ex.getMessage());
        return  problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerException(Exception ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("unexpected error ");
        problemDetail.setDetail("An unexpected error occurred. Please contact support.");
        return problemDetail;
    }
}
