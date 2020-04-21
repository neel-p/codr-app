package com.codr.aws.exceptions;

public class UserTypeNotFoundException extends RuntimeException {

    public UserTypeNotFoundException(String message) {
        super(message);
    }

    public UserTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
