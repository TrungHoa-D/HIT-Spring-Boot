package com.example.homework2.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter@Setter@AllArgsConstructor
public class AlreadyExistException extends Exception{
    private String message;
    private HttpStatus status;
    public AlreadyExistException(String message) {
        this.message =message;
        status = HttpStatus.BAD_REQUEST;
    }
}
