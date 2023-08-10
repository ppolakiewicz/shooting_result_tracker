package com.str.shootingresulttracker.magazine;

import com.str.shootingresulttracker.kernel.AbstractBaseDomainError;

public class FullMagazineError extends AbstractBaseDomainError {

    private static final String ERROR_KEY = "magazine.error.magazineIsFull";
    private static final String ERROR_MESSAGE = "Magazine reached it max capacity: ";

    public FullMagazineError(long magazineCapacity) {
        super(ERROR_KEY, ERROR_MESSAGE + magazineCapacity);
    }
}
