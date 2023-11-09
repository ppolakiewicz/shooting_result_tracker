package com.str.shootingresulttracker.domain.kernel;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AbstractBaseDomainEntityTest extends AbstractUnitTest {

    @Test
    @DisplayName("base domain entity should be created properly")
    void baseDomainEntityShouldBeCreatedProperly() {
        //given
        UUID createdBy = UUID.randomUUID();

        //when
        AbstractBaseDomainEntity entity = new AbstractBaseDomainEntity(clock, createdBy);

        //then
        Assertions.assertEquals(createdBy, entity.getCreatedBy());
    }

    @Test
    @DisplayName("base domain entity can not be created without creator id")
    void baseDomainEntityCanNotBeCreatedWithoutCreatorId() {
        //then
        Assertions.assertThrows(NullPointerException.class, () -> new AbstractBaseDomainEntity(clock, null));
    }
}
