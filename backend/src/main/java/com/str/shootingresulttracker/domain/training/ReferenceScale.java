package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.model.Distance;

import java.util.Objects;

public record ReferenceScale(
        Distance distance,
        double pictureLength
) {

    public ReferenceScale {
        Objects.requireNonNull(distance, "Distance can not be empty");
    }
}
