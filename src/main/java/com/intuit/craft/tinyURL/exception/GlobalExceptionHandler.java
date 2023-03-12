package com.intuit.craft.tinyURL.exception;

import com.intuit.craft.tinyURL.exception.ErrorResponse;
import com.intuit.craft.tinyURL.exception.InvalidUrlException;
import com.intuit.craft.tinyURL.exception.NoSuchUrlException;
import com.intuit.craft.tinyURL.exception.UrlAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception Handling -- Create a seperate class for ExceptionHandler
    @ExceptionHandler(value = InvalidUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidUrlException(InvalidUrlException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchUrlException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchUrlException(NoSuchUrlException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage());
    }

    @ExceptionHandler(value = UrlAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUrlAlreadyExistsException(UrlAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
