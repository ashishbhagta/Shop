package com.DCMetal.Shop.exceptions;

public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }
}
