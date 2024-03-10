package com.str.shootingresulttracker.domain.training.error;

import com.str.shootingresulttracker.domain.model.AbstractBaseDomainError;

public class FullTrainingError extends AbstractBaseDomainError {

    private static final String ERROR_KEY = "training.error.trainingIsFull";
    private static final String ERROR_MESSAGE = "Training reached it max capacity: ";

    public FullTrainingError(int trainingCapacity) {
        super(ERROR_KEY, ERROR_MESSAGE + trainingCapacity);
    }
}
