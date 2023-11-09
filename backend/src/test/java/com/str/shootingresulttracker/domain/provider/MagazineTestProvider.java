package com.str.shootingresulttracker.domain.provider;

import com.str.shootingresulttracker.domain.magazine.Magazine;

import java.time.Clock;
import java.util.UUID;

public class MagazineTestProvider {

    public static Magazine create(Clock clock) {

        return new Magazine(
                "Test magazine",
                UUID.randomUUID(),
                clock
        );
    }
}
