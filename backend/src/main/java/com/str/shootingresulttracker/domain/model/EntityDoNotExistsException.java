package com.str.shootingresulttracker.domain.model;

public class EntityDoNotExistsException extends AbstractDomainException {

    private static final String ERROR_KEY = "entity.doNotExists";
    private static final String ERROR_MESSAGE = "Entity with given ID do not exists";

    public EntityDoNotExistsException() {
        super(ERROR_KEY, ERROR_MESSAGE);
    }
}
