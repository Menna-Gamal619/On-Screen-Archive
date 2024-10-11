package com.Movies.On_Screen.Archive.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class GlobalExcepionHandler {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidArgumentHandler(MethodArgumentNotValidException ex){
        return exceptionHandler(ex, HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"IllegalArgumentExceotion");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentHandler(IllegalArgumentException ex){
        return exceptionHandler(ex,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"IllegalArgumentException");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException ex){
        return exceptionHandler(ex,HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customExceptionHandler(CustomException ex){
        String message= messageSource.getMessage(ex.getMessage(), null,Locale.ENGLISH);
        return exceptionHandler(ex,ex.getStatus(),ex.getStatus().value(),message);
    }
    private ResponseEntity<ExceptionResponse> exceptionHandler(Exception ex, HttpStatus status, int code, String message) {
    String msg;
    try {
        msg=messageSource.getMessage(message,null, Locale.ENGLISH);
    }catch (Exception exception){
        System.err.println("Could not find the message in resources");
        msg=message;
    }
    return new ResponseEntity<ExceptionResponse>(
            new ExceptionResponse(msg , status , code, LocalDateTime.now()),status );

    }
}
