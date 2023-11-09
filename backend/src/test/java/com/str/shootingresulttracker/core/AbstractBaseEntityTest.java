package com.str.shootingresulttracker.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractBaseEntityTest extends AbstractUnitTest {

    @Test
    @DisplayName("base entity should be created properly")
    void baseEntityShouldBeCreatedProperly() {
        //given
        setClock(2020, 1, 1);

        //when
        AbstractBaseEntity entity = new AbstractBaseEntity(clock) {
        };

        //then
        assertEquals(entity.getCreationDate().toInstant(), getClock());
    }

    @Test
    @DisplayName("base entity can not be created without clock")
    void baseEntityCanNotBeCreatedWithoutClock() {
        //expect
        assertThrows(NullPointerException.class, () -> new AbstractBaseEntity(null) {
        });
    }

}