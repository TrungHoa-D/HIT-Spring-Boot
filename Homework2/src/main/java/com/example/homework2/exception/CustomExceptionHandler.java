package com.example.homework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ErrorResponse handlerAlreadyExistException(AlreadyExistException ex, WebRequest request) {
        return  new ErrorResponse("Tai khoan da ton tai", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ErrorResponse handlerIndexOutOfBound(IndexOutOfBoundsException ex, WebRequest request) {
        return new ErrorResponse("Vuot qua mang", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordNotMatchException.class)
    public ErrorResponse handlerPasswordNotMatchException(PasswordNotMatchException ex, WebRequest request) {
        return  new ErrorResponse("Mật khẩu không khớp", HttpStatus.BAD_REQUEST);
    }
}
