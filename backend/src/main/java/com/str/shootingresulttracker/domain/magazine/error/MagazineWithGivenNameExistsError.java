package com.str.shootingresulttracker.domain.magazine.error;

import com.str.shootingresulttracker.domain.kernel.AbstractBaseDomainError;

public class MagazineWithGivenNameExistsError extends AbstractBaseDomainError {

    private static final String ERROR_KEY = "magazine.error.magazineWithGivenNameExists";

    public MagazineWithGivenNameExistsError(String magazineName) {
        super(ERROR_KEY, "Magazine with name " + magazineName + " already exists");
    }
}
