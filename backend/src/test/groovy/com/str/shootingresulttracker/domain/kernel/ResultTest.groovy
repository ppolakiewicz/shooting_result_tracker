package com.str.shootingresulttracker.domain.kernel

import spock.lang.Specification

class ResultTest extends Specification {

    void 'when creating result with value then value should exists and error should not exists'() {
        given:
            Result<Integer, TestDomainError> result = new Result<>(1)

        expect:
            result.getValue().isPresent()
            result.getValue().get() == 1
            result.getError().isEmpty();
    }

    void 'when creating result with error then error should exists and value should not exists'() {
        given:
            TestDomainError error = new TestDomainError()
            Result<Integer, TestDomainError> result = new Result<>(error)

        expect:
            result.getValue().isEmpty()
            result.getError().isPresent()
            result.getError().get() == error
    }

    void 'when mapping result that have error then no mapping should be applied and same result returned'() {
        given:
            TestDomainError error = new TestDomainError()
            Result<Integer, TestDomainError> baseResult = new Result<>(error)
            Result<Integer, TestDomainError> expectedResult = new Result<>(error)

        when:
            Result<Integer, TestDomainError> result = baseResult.mapValue { value -> value * 2 }

        then:
            result.getValue().isEmpty()
            result.getError().isPresent()
            result == expectedResult
    }

    void 'when mapping result that have value then result with mapped value should be returned'() {
        given:
            Result<Integer, TestDomainError> baseResult = new Result<>(1)
            Result<Integer, TestDomainError> expectedResult = new Result<>(2)

        when:
            Result<Integer, TestDomainError> result = baseResult.mapValue { value -> value * 2 }

        then:
            result == expectedResult
    }

    void 'when result contains value then orElseThrow should return value and not throw any exception'() {
        given:
            Result<Integer, TestDomainError> baseResult = new Result<>(1)

        when:
            Integer value = baseResult.orElseThrow((TestDomainError testError) -> new TestRuntimeException())

        then:
            noExceptionThrown()
            value == 1
    }

    void 'when result contains error then orElseThrow should throw provided exception'() {
        given:
            TestDomainError error = new TestDomainError()
            Result<Integer, TestDomainError> baseResult = new Result<>(error)

        when:
            baseResult.orElseThrow((TestDomainError testError) -> new TestRuntimeException())

        then:
            thrown TestRuntimeException
    }
}
