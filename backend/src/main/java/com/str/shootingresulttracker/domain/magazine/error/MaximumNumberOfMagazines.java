package com.str.shootingresulttracker.domain.magazine.error;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class MaximumNumberOfMagazines extends AbstractBaseDomainError {

    private static final String ERROR_KEY = "magazine.error.maximumNumberOfMagazines";
    private static final String ERROR_MESSAGE = "Reached maximum number of magazines";

    public MaximumNumberOfMagazines() {
        super(ERROR_KEY, ERROR_MESSAGE);
    }
}
