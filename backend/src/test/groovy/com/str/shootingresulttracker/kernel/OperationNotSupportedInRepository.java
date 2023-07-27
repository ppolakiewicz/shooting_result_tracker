package com.str.shootingresulttracker.kernel;

class OperationNotSupportedInRepository extends RuntimeException{

    public OperationNotSupportedInRepository(String message) {
        super(message);
    }
}
