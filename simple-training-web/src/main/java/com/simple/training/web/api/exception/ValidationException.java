package com.simple.training.web.api.exception;

public abstract class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ValidationException(String detailMessage) {
        super(detailMessage);
    }

}
