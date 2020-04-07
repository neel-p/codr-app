package com.codr.aws.exceptions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BadInputException extends RuntimeException {

    private Set<String> errors;

    public BadInputException(String message) {
        super(message);
        this.errors = Collections.unmodifiableSet(new HashSet<>());
    }

    public BadInputException(Set<String> errors) {
        super("Bad input payload");
        this.errors = Collections.unmodifiableSet(errors);
    }

    public Set<String> getErrors() {
        return errors;
    }
}
