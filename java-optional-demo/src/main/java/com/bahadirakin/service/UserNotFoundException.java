package com.bahadirakin.service;

public class UserNotFoundException extends Exception {

    private final String username;

    public UserNotFoundException(final String username) {
        super(String.format("User for %s username was not found", username));
        this.username = username;
    }

    public UserNotFoundException(String username, Throwable cause) {
        super(String.format("User for %s username was not found", username), cause);
        this.username = username;
    }
}
