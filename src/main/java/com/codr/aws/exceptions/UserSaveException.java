package com.codr.aws.exceptions;

public class UserSaveException extends RuntimeException {

    public UserSaveException(String message) {
        super(message);
    }

    public UserSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserSaveException(Throwable cause) {
        super(cause);
    }
}
