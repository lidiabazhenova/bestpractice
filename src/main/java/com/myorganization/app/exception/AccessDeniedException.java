package com.myorganization.app.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(final String message) {
        super(message);
    }
}
