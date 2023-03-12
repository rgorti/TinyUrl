package com.intuit.craft.tinyURL.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoSuchUrlException extends RuntimeException{

    private String message;

    public NoSuchUrlException() {}

    public NoSuchUrlException(String msg) {
        super(msg);
        this.message = msg;
    }
}
