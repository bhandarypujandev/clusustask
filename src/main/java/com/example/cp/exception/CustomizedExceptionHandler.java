package com.example.cp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> handleException(InvalidFileException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("File is Invalid ! Please upload valid file csv");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Object> handleException(InvalidFieldException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Invalid Field");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyFileException.class)
    public ResponseEntity<Object> handleException(EmptyFileException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("File is Empty!");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OtherExceptions.class)
    public ResponseEntity<Object> handleException(OtherExceptions exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        response.setCode(exception.getCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
