package com.zentity.demo.jsonplaceholder.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int userId) {
        super("" + userId);
    }
}
