package com.str.shootingresulttracker.domain.kernel;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class TestDomainError extends AbstractBaseDomainError {

    public TestDomainError() {
        super("Test", "Test message");
    }
}