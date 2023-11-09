package com.str.shootingresulttracker.domain.magazine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmmunitionTest {

    @Test
    @DisplayName("empty ammunition should contains given caliber and zero quantity")
    void emptyAmmunitionShouldContainsGivenCaliberAndZeroQuantity() {
        //given
        Caliber caliber = Caliber.PARABELLUM_9X19;

        //when
        Ammunition result = Ammunition.empty(caliber);

        //then
        assertEquals(caliber, result.caliber());
        assertEquals(0, result.quantity());
    }

    @Test
    @DisplayName("when quantity is added then new ammunition object with same caliber and sum of quantity should be returned")
    void whenQuantityIsAddedThenNewAmmunitionObjectWithSameCaliberAndSumOfQuantityShouldBeReturned() {
        //given
        Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10);

        //when
        var result = ammunition.addQuantity(10);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(result.getValue().isPresent());
        assertNotEquals(ammunition, result.getValue().get());
        assertEquals(20, result.getValue().get().quantity());
    }

    @Test
    @DisplayName("when negative value is added to quantity then error is returned")
    void whenNegativeValueIsAddedToQuantityThenErrorIsReturned() {
        //given
        Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10);

        //when
        var result = ammunition.addQuantity(-5);

        //then
        assertTrue(result.getValue().isEmpty());
        assertTrue(result.getError().isPresent());
    }

    @Test
    @DisplayName("when quantity is subtracted then new ammunition object with same caliber and quantity difference should be returned")
    void whenQuantityIsSubtractedThenNewAmmunitionObjectWithSameCaliberAndQuantityDifferenceShouldBeReturned() {
        //given
        Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10);

        //when
        var result = ammunition.subtractQuantity(5);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(result.getValue().isPresent());
        assertNotEquals(ammunition, result.getValue().get());
        assertEquals(5, result.getValue().get().quantity());
    }

    @Test
    @DisplayName("ammunition quantity can not be lower than zero")
    void ammunitionQuantityCanNotBeLowerThanZero() {
        //given
        Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10);

        //when
        var result = ammunition.subtractQuantity(15);

        //then
        assertTrue(result.getError().isEmpty());
        assertTrue(result.getValue().isPresent());
        assertNotEquals(ammunition, result.getValue().get());
        assertEquals(0L, result.getValue().get().quantity());
    }

    @Test
    @DisplayName("when negative value is subtracted from quantity then error is returned")
    void whenNegativeValueIsSubtractedFromQuantityThenErrorIsReturned() {
        //given
        Ammunition ammunition = new Ammunition(Caliber.PARABELLUM_9X19, 10);

        //when
        var result = ammunition.subtractQuantity(-5);

        //then
        assertTrue(result.getValue().isEmpty());
        assertTrue(result.getError().isPresent());
    }

    @Test
    @DisplayName("ammunition can not be initialized without caliber")
    void ammunitionCanNotBeInitializedWithoutCaliber() {
        assertThrows(NullPointerException.class, () -> new Ammunition(null, 0));
    }

    @Test
    @DisplayName("ammunition can not be initialized with negative quantity")
    void ammunitionCanNotBeInitializedWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Ammunition(Caliber.ACP_45, -5));
    }

}
