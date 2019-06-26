package com.zentity.demo.jsonplaceholder.exception;

import io.netty.handler.timeout.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionConfiguration.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>("user not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    ResponseEntity<String> handleBackendTimeout() {
        return new ResponseEntity<>("One or more backend services not responding.", HttpStatus.GATEWAY_TIMEOUT);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<String> genericException(RuntimeException e) {
        logger.error("Unknown error.", e);
        return new ResponseEntity<>("Unknown error occured, please contact support.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
