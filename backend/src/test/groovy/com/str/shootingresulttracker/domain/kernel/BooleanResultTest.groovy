package com.str.shootingresulttracker.domain.kernel

import spock.lang.Specification

import java.util.concurrent.atomic.AtomicInteger

class BooleanResultTest extends Specification {

    void 'when result is success then success handler should be executed'(){
        given:
            AtomicInteger i = new AtomicInteger(1)
            BooleanResult<TestDomainError> result = BooleanResult.success()

        when:
            result.ifSuccess(() -> i.incrementAndGet())

        then:
            i.get() == 2
    }

    void 'when result is fail then success handler should not be executed'(){
        given:
            AtomicInteger i = new AtomicInteger(1)
            BooleanResult<TestDomainError> result = BooleanResult.fail()

        when:
            result.ifSuccess(() -> i.incrementAndGet())

        then:
            i.get() == 1
    }
}
