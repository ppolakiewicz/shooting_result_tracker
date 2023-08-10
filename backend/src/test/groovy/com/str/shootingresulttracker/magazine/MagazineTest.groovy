package com.str.shootingresulttracker.magazine

import com.str.shootingresulttracker.provider.WeaponTestProvider
import spock.lang.Specification
import spock.util.time.MutableClock

import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime

class MagazineTest extends Specification {

    void 'when magazine is created then creation date should be set according do provided clock'() {
        given:
            ZonedDateTime defaultTime = ZonedDateTime.of(2018, 6, 5, 0, 0, 0, 0, ZoneId.of('UTC'))
            Clock clock = new MutableClock(defaultTime)

        when:
            Magazine magazine = new Magazine(
                    'Test magazine',
                    UUID.randomUUID(),
                    clock
            )

        then:
            magazine.getCreationDate().toZonedDateTime().isEqual(defaultTime)
    }

    void 'when magazine reaches his maximum capacity no new weapon can be added'() {
        given: 'new empty magazine'
            Clock clock = new MutableClock()

            Magazine magazine = new Magazine(
                    'Test magazine',
                    UUID.randomUUID(),
                    clock
            )

        and: 'magazine filled with weapons'
            for (i in 0..<50) {
                magazine.addWeapon(WeaponTestProvider.create())
            }

        when: 'tried to add weapon to full magazine'
            def result = magazine.addWeapon(WeaponTestProvider.create())

        then:
            result.getError().isPresent()
            FullMagazineError.isCase(result.getError().get())

    }

}
