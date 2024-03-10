package com.str.shootingresulttracker.web.kernel;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class WebException extends RuntimeException{

    private final String errorKey;

    public WebException(String errorKey, String errorMessage) {
        super(errorMessage);
        this.errorKey = errorKey;
    }

    public static WebException fromDomainError(AbstractBaseDomainError domainError){
        return new WebException(domainError.getErrorKey(), domainError.getErrorMessage());
    }
}
