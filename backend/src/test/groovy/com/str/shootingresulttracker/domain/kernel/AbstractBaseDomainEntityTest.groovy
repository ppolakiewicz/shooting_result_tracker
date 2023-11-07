package com.str.shootingresulttracker.domain.kernel

import com.str.shootingresulttracker.core.AbstractUnitTest

class AbstractBaseDomainEntityTest extends AbstractUnitTest {

    void 'base domain entity should be created properly'() {
        given:
            UUID createdBy = UUID.randomUUID()

        when:
            AbstractBaseDomainEntity entity = new AbstractBaseDomainEntity(clock, createdBy)

        then:
            entity.getCreatedBy() == createdBy
    }

    void 'base domain entity can not be created without creator id'() {
        when:
            new AbstractBaseDomainEntity(clock, null)

        then:
            thrown NullPointerException
    }

}
