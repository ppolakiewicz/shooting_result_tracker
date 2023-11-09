package com.str.shootingresulttracker.core;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public abstract class AbstractUnitTest {

    protected Clock clock = Clock.systemUTC();

    protected OffsetDateTime setClock(int year, int month, int day) {
        var offsetDateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC);
        this.clock = Clock.fixed(offsetDateTime.toInstant(), ZoneOffset.UTC);
        return offsetDateTime;
    }


    protected Instant getClock() {
        return Instant.now(clock);
    }

}
