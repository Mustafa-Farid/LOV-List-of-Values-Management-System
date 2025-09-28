package com.Beneficiary.lovlist.exceptionHandling;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandling extends RuntimeException {
    public GlobalExceptionHandling(){

    }

    public GlobalExceptionHandling(String message){
        super(message);
    }
}
