package com.str.shootingresulttracker.core

import spock.lang.Shared
import spock.lang.Specification
import spock.util.time.MutableClock

import java.time.Clock

class AbstractUnitTest extends Specification{

    @Shared
    protected Clock clock = new MutableClock()

}
