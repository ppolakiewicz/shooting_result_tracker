package com.str.shootingresulttracker.core

import spock.lang.Shared
import spock.lang.Specification
import spock.util.time.MutableClock

import java.time.Clock
import java.time.OffsetDateTime
import java.time.ZoneOffset

class AbstractUnitTest extends Specification {

    @Shared
    protected Clock clock = new MutableClock()

    protected OffsetDateTime setClock(int year, int month, int day) {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC)
        clock.setInstant(offsetDateTime.toInstant())
        return offsetDateTime
    }

}
