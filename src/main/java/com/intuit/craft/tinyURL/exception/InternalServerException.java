package com.intuit.craft.tinyURL.exception;

public class InternalServerException extends RuntimeException{

    private String message;

    public InternalServerException() {}

    public InternalServerException(String msg) {
        super(msg);
        this.message = msg;
    }

    public InternalServerException(String detail, Throwable cause) {
        super(detail, cause);
    }
}
