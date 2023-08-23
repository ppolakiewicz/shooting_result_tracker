package com.str.shootingresulttracker.magazine


import com.str.shootingresulttracker.weapon.Caliber
import spock.lang.Specification

class AmmunitionTest extends Specification {

    void 'empty ammunition should contains given caliber and zero quantity'() {
        given:
            Caliber caliber = Caliber.PARABELLUM_9X19

        when:
            Ammunition result = Ammunition.empty(caliber)

        then:
            result.caliber() == caliber
            result.quantity() == 0
    }

    void 'when quantity is added then new ammunition object with same caliber and sum of quantity should be returned'() {
        given:
            Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10)

        when:
            def result = ammunition.addQuantity(10)

        then:
            result.error.isEmpty()
            result.value.get() != ammunition
            result.value.get().quantity() == 20
    }

    void 'when negative value is added to quantity then error is returned'() {
        given:
            Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10)

        when:
            def result = ammunition.addQuantity(-5)

        then:
            result.value.isEmpty()
            result.error.isPresent()
    }

    void 'when quantity is subtracted then new ammunition object with same caliber and quantity difference should be returned'() {
        given:
            Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10)

        when:
            def result = ammunition.subtractQuantity(5)

        then:
            result.error.isEmpty()
            result.value.get() != ammunition
            result.value.get().quantity() == 5
    }

    void 'ammunition quantity can not be lower than zero'() {
        given:
            Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10)

        when:
            def result = ammunition.subtractQuantity(15)

        then:
            result.error.isEmpty()
            result.value.get() != ammunition
            result.value.get().quantity() == 0
    }

    void 'when negative value is subtracted from quantity then error is returned'() {
        given:
            Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10)

        when:
            def result = ammunition.subtractQuantity(-5)

        then:
            result.value.isEmpty()
            result.error.isPresent()
    }

    void 'ammunition can not be initialized without caliber'() {
        when:
            new Ammunition(null, 0)

        then:
            thrown NullPointerException
    }

    void 'ammunition can not be initialized with negative quantity'() {
        when:
            new Ammunition(Caliber.ACP_45, -5)

        then:
            thrown IllegalArgumentException
    }


}
