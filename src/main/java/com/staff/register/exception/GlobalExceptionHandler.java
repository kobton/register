package com.staff.register.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatePersonException.class)
    public ResponseEntity<String> handleDuplicatePersonException(DuplicatePersonException ex) {
        System.err.println("DuplicatePersonException occurred: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException ex) {
        System.err.println("An error occurred: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("An error occurred: " + ex.getMessage());
    }
}