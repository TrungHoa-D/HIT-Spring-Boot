package com.example.lesson5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handlerNotFoundException(NotFoundException ex, WebRequest request) {
        return  new ErrorResponse(ex.getMessage(), ex.getStatus());
    }
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ErrorResponse handlerIndexOutOfBound(IndexOutOfBoundsException ex, WebRequest request) {
        return new ErrorResponse("Vuot qua mang", HttpStatus.BAD_REQUEST);
    }
}
