package com.codr.aws.exception;

import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;

public class DuplicateEmailException extends AWSCognitoIdentityProviderException {

    /**
     * long
     */
    private static final long serialVersionUID = 6561571810771139916L;

    public DuplicateEmailException(final String errorMessage) {
        super(errorMessage);
    }
}
