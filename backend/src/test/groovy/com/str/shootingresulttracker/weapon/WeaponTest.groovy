package com.str.shootingresulttracker.weapon

import spock.lang.Specification
import spock.util.time.MutableClock

import java.time.Clock
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class WeaponTest extends Specification {

    void 'when weapon is created then creation date should be set based on provided clock'() {
        given:
            ZonedDateTime defaultTime = ZonedDateTime.of(2018, 6, 5, 0, 0, 0, 0, ZoneId.of('UTC'))
            Clock clock = new MutableClock(defaultTime)

        when:
            Weapon weapon = new Weapon(
                    'Test',
                    WeaponType.PISTOL,
                    Caliber.ACP_45,
                    'Test model',
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    clock
            )

        then:
            weapon.getCreationDate().toZonedDateTime().isEqual(defaultTime)
    }
}
