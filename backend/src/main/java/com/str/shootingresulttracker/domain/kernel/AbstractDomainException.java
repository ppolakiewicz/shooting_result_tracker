package com.str.shootingresulttracker.domain.kernel;

import java.util.Objects;

public abstract class AbstractDomainException extends RuntimeException{

    private final String errorKey;
    private final String errorMessage;

    public AbstractDomainException(String errorKey, String errorMessage) {
        Objects.requireNonNull(errorKey);
        Objects.requireNonNull(errorMessage);

        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
