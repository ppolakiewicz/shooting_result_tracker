package com.str.shootingresulttracker.core;

class OperationNotSupportedInRepository extends RuntimeException {

    public OperationNotSupportedInRepository(String message) {
        super(message);
    }
}
