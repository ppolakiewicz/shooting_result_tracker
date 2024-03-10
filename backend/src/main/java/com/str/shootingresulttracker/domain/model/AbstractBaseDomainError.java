package com.str.shootingresulttracker.domain.model;

import java.util.Objects;

public class AbstractBaseDomainError {

    private final String errorKey;
    private final String errorMessage;

    public AbstractBaseDomainError(String errorKey, String errorMessage) {
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
