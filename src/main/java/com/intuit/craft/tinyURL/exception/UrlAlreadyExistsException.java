package com.intuit.craft.tinyURL.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UrlAlreadyExistsException extends RuntimeException{

    private String message;

    public UrlAlreadyExistsException() {}

    public UrlAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}

