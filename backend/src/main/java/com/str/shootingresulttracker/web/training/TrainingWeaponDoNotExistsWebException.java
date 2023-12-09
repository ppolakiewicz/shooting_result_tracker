package com.str.shootingresulttracker.web.training;

import com.str.shootingresulttracker.web.kernel.WebException;

class TrainingWeaponDoNotExistsWebException extends WebException {

    private static final String ERROR_KEY = "training.weaponDoNotExists";
    private static final String ERROR_MESSAGE = "Could not find provided training weapon";

    public TrainingWeaponDoNotExistsWebException() {
        super(ERROR_KEY, ERROR_MESSAGE);
    }
}
