package com.str.shootingresulttracker.authentication;

import lombok.Getter;

@Getter
class AuthenticationException extends RuntimeException{

    private final String exceptionCode;

    public AuthenticationException(String exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
