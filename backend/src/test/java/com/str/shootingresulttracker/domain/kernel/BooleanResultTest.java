package com.str.shootingresulttracker.domain.kernel;

import com.str.shootingresulttracker.domain.model.BooleanResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class BooleanResultTest {

    @Test
    @DisplayName("when result is success then success handler should be executed")
    void whenResultIsSuccessThenSuccessHandlerShouldBeExecuted() {
        //given
        AtomicInteger i = new AtomicInteger(1);
        BooleanResult<TestDomainError> result = BooleanResult.success();

        //when
        result.ifSuccess(i::incrementAndGet);

        //then
        Assertions.assertEquals(2, i.get());
    }

    @Test
    @DisplayName("when result is fail then success handler should not be executed")
    void whenResultIsFailThenSuccessHandlerShouldNotBeExecuted() {
        //given
        AtomicInteger i = new AtomicInteger(1);
        BooleanResult<TestDomainError> result = BooleanResult.fail(new TestDomainError());

        //when
        result.ifSuccess(i::incrementAndGet);

        //then
        Assertions.assertEquals(1, i.get());
    }
}
