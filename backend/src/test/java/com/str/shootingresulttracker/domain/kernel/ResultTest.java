package com.str.shootingresulttracker.domain.kernel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    @DisplayName("when creating result with value then value should exists and error should not exists")
    void whenCreatingResultWithvaluethenvalueshouldexistsanderrorshouldnotexists() {
        //given
        Result<Integer, TestDomainError> result = new Result<>(1);

        //expect
        assertTrue(result.isValue());
        assertEquals(1, result.getValue());
        assertFalse(result.isError());
    }

    @Test
    @DisplayName("when creating result with error then error should exists and value should not exists")
    void WhenCreatingResultWithErrorThenErrorShouldExistsAndValueShouldNotExists() {
        //given
        TestDomainError error = new TestDomainError();
        Result<Integer, TestDomainError> result = new Result<>(error);

        //expect
        assertFalse(result.isValue());
        assertTrue(result.isError());
        assertEquals(result.getError(), error);
    }

    @Test
    @DisplayName("when mapping result that have error then no mapping should be applied and same result returned")
    void whenMappingResultThatHaveErrorThenNoMappingShouldBeAppliedAndSameResultReturned() {
        //given
        TestDomainError error = new TestDomainError();
        Result<Integer, TestDomainError> baseResult = new Result<>(error);
        Result<Integer, TestDomainError> expectedResult = new Result<>(error);

        //when
        Result<Integer, TestDomainError> result = baseResult.mapValue(value -> value * 2);

        //then
        assertFalse(result.isValue());
        assertTrue(result.isError());
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("when mapping result that have value then result with mapped value should be returned")
    void whenMappingResultThatHaveValueThenResultWithMappedValueShouldBeReturned() {
        //given
        Result<Integer, TestDomainError> baseResult = new Result<>(1);
        Result<Integer, TestDomainError> expectedResult = new Result<>(2);

        //when
        Result<Integer, TestDomainError> result = baseResult.mapValue(value -> value * 2);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("when result contains value then orElseThrow should return value and not throw any exception")
    void whenResultContainsValueThenOrElseThrowShouldReturnValueAndNotThrowAnyException() {
        //given
        Result<Integer, TestDomainError> baseResult = new Result<>(1);

        //then
        assertDoesNotThrow(() -> {
            Integer value = baseResult.orElseThrow((TestDomainError testError) -> new TestRuntimeException());
            assertEquals(1, value);
        });
    }

    @Test
    @DisplayName("when result contains error then orElseThrow should throw provided exception")
    void whenResultContainsErrorThenOrElseThrowShouldThrowProvidedException() {
        //given
        TestDomainError error = new TestDomainError();
        Result<Integer, TestDomainError> baseResult = new Result<>(error);

        //then
        assertThrows(TestRuntimeException.class, () -> baseResult.orElseThrow((TestDomainError testError) -> new TestRuntimeException()));
    }

}
