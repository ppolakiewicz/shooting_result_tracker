package com.str.shootingresulttracker.core

import java.time.OffsetDateTime

class AbstractBaseEntityTest extends AbstractUnitTest {

    void 'base entity should be created properly'() {
        given:
            OffsetDateTime clockTime = setClock(2020, 1, 1)

        when:
            AbstractBaseEntity entity = new AbstractBaseEntity(clock) {}

        then:
            entity.getCreationDate().toInstant() == clockTime.toInstant()
    }

    void 'base entity can not be created without clock'() {
        when:
            new AbstractBaseEntity(null) {}

        then:
            thrown NullPointerException

    }
}
