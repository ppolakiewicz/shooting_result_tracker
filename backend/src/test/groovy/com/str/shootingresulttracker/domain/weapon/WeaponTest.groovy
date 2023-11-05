package com.str.shootingresulttracker.domain.weapon

import com.str.shootingresulttracker.domain.magazine.Caliber
import com.str.shootingresulttracker.domain.magazine.Weapon
import com.str.shootingresulttracker.domain.magazine.WeaponType
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
            UUID createdBy = UUID.randomUUID()

        when:
            Weapon weapon = new Weapon(
                    'Test',
                    WeaponType.PISTOL,
                    Caliber.ACP_45,
                    'Test model',
                    OffsetDateTime.now(),
                    OffsetDateTime.now(),
                    createdBy,
                    clock
            )

        then:
            weapon.getCreationDate().toZonedDateTime().isEqual(defaultTime)
    }

    void 'weapon can be created without production and purchase date'() {
        given:
            ZonedDateTime defaultTime = ZonedDateTime.of(2018, 6, 5, 0, 0, 0, 0, ZoneId.of('UTC'))
            Clock clock = new MutableClock(defaultTime)
            UUID createdBy = UUID.randomUUID()

        when:
            Weapon weapon = new Weapon(
                    'Test',
                    WeaponType.PISTOL,
                    Caliber.ACP_45,
                    'Test model',
                    null,
                    null,
                    createdBy,
                    clock
            )

        then:
            noExceptionThrown()
    }
}
