package com.str.shootingresulttracker.provider;

import com.str.shootingresulttracker.magazine.Magazine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import spock.util.time.MutableClock;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MagazineTestProvider {

    public static Magazine create() {

        return new Magazine(
                "Test magazine",
                UUID.randomUUID(),
                new MutableClock()
        );
    }

}
