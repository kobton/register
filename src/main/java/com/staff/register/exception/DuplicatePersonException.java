package com.staff.register.exception;

public class DuplicatePersonException extends RuntimeException {

    public DuplicatePersonException(String message) {
        super(message);
    }

    public DuplicatePersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
