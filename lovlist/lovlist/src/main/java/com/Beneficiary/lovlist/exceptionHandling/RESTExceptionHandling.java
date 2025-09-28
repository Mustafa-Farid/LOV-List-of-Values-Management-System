package com.Beneficiary.lovlist.exceptionHandling;

import com.Beneficiary.lovlist.response.LOVListRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class RESTExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<LOVListRes> excHandler(GlobalExceptionHandling exc)
    {
        LOVListRes error = new LOVListRes();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
