package com.str.shootingresulttracker.domain.magazine.error;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class AmmunitionQuantityError extends AbstractBaseDomainError {

    private static final String ERROR_KEY = "magazine.error.ammunitionQuantity";
    private static final String ERROR_MESSAGE = "Invalid ammunition quantity modification value: ";

    public AmmunitionQuantityError(long quantityModification) {
        super(ERROR_KEY, ERROR_MESSAGE + quantityModification);
    }
}
