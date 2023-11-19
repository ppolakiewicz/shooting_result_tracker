package com.str.shootingresulttracker.domain.kernel;

public class DomainResult<V> extends Result<V, AbstractBaseDomainError> {

    public DomainResult(AbstractBaseDomainError error) {
        super(error);
    }

    public DomainResult(V value) {
        super(value);
    }
}
