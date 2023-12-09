package com.str.shootingresulttracker.web.training;

import com.str.shootingresulttracker.web.kernel.WebException;

class TrainingDoNotExistsWebException extends WebException {

    private static final String ERROR_KEY = "training.doNotExists";
    private static final String ERROR_MESSAGE = "Training with given ID do not exists";

    public TrainingDoNotExistsWebException() {
        super(ERROR_KEY, ERROR_MESSAGE);
    }
}
