package com.str.shootingresulttracker.domain.magazine

import com.str.shootingresulttracker.domain.provider.MagazineTestProvider
import com.str.shootingresulttracker.domain.provider.WeaponTestProvider
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
    }

    void 'when magazine do not contains ammunition that is added then new ammunition should be added to magazine'() {
        given:
            Magazine magazine = MagazineTestProvider.create()
            Ammunition newAmmunition = new Ammunition(Caliber.ACP_45, 123)

        when:
            def result = magazine.addAmmunition(newAmmunition)

        then:
            result.getValue().get() == true
            magazine.getAmmunition().contains(newAmmunition)
    }

    void 'when magazine already contains ammunition for given caliber and same caliber ammunition is added then quantity should be summed'() {
        given:
            Caliber caliber = Caliber.ACP_45
            Ammunition existingAmmunition = new Ammunition(caliber, 10)

        and: 'magazine with ammunition for given caliber'
            Magazine magazine = MagazineTestProvider.create()
            magazine.addAmmunition(existingAmmunition)

        and: 'new pack of ammunition'
            Ammunition newPackOfAmmunition = new Ammunition(caliber, 25)

        and: 'expected result'
            Ammunition expectedAmmunition = new Ammunition(caliber, 35)

        when:
            def result = magazine.addAmmunition(newPackOfAmmunition)

        then:
            result.getValue().isPresent()
            result.getValue().get() == true
            magazine.getAmmunition().contains(expectedAmmunition)
    }

    void 'when subtracting ammunition from magazine that do not exists in magazine then magazine should still do not contains this ammunition'() {
        given: 'magazine with some ammunition'
            Magazine magazine = MagazineTestProvider.create()
            magazine.addAmmunition(new Ammunition(Caliber.ACP_45, 10))
            magazine.addAmmunition(new Ammunition(Caliber.ACP_380, 10))
            magazine.addAmmunition(new Ammunition(Caliber.PARABELLUM_9X19, 10))

        and: 'ammunition that is not stored in magazine'
            Caliber notStoredCaliber = Caliber.CASULL_454
            Ammunition notStoredAmmunition = new Ammunition(notStoredCaliber, 44)

        when:
            def result = magazine.subtractAmmunition(notStoredAmmunition)

        then:
            result.getError().isEmpty()
            magazine.getAmmunition().stream()
                    .noneMatch { ammunition -> (ammunition.caliber() == notStoredCaliber) }
    }

    void 'when subtracting ammunition from magazine then quantity should be subtracted'() {
        given:
            Caliber caliber = Caliber.ACP_45
            Magazine magazine = MagazineTestProvider.create()
            magazine.addAmmunition(new Ammunition(caliber, 10))

        and:
            Ammunition subtractedAmmunition = new Ammunition(caliber, 3)

        and:
            Ammunition expectedResult = new Ammunition(caliber, 7)

        when:
            def result = magazine.subtractAmmunition(subtractedAmmunition)

        then:
            result.getError().isEmpty()
            magazine.getAmmunition().contains(expectedResult)
    }

    void 'when whole ammunition for given caliber is subtracted then magazine should not contain given caliber'() {
        given:
            Caliber caliber = Caliber.ACP_45
            Magazine magazine = MagazineTestProvider.create()
            magazine.addAmmunition(new Ammunition(caliber, 10))

        and:
            Ammunition subtractedAmmunition = new Ammunition(caliber, 20)

        when:
            def result = magazine.subtractAmmunition(subtractedAmmunition)

        then:
            result.getError().isEmpty()
            magazine.getAmmunition().stream()
                    .noneMatch { ammunition -> ammunition.caliber() == caliber }
    }

    void 'magazine name can be changed to non empty value'() {
        given:
            String newMagazineName = 'New magazine name'
            Magazine magazine = MagazineTestProvider.create()

        expect:
            magazine.getName() != newMagazineName

        when:
            magazine.setName(newMagazineName)

        then:
            magazine.getName() == newMagazineName
    }

    void 'magazine can not have empty name'(){
        given:
            String empty = ''
            UUID ownerId = UUID.randomUUID()
            Clock clock = new MutableClock()

        when:
            new Magazine(empty, ownerId, clock)

        then:
            thrown IllegalArgumentException
    }



}
