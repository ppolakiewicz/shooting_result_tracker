package com.str.shootingresulttracker.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Distance {

    private final float meters;

    private Distance(float meters) {
        this.meters = meters;
    }

    public static Distance ofMeters(float meters) {
        return new Distance(meters);
    }

}
