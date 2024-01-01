package com.str.shootingresulttracker.domain.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Distance {

    private final double meters;

    private Distance(double meters) {
        this.meters = meters;
    }

    public static Distance ofMeters(double meters) {
        return new Distance(meters);
    }

    public double getInMeters() {
        return meters;
    }

}
