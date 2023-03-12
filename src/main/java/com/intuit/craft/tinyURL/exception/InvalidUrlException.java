package com.intuit.craft.tinyURL.exception;

public class InvalidUrlException extends RuntimeException{

    private String message;

    public InvalidUrlException() {}

    public InvalidUrlException(String msg) {
        super(msg);
        this.message = msg;
    }
}

