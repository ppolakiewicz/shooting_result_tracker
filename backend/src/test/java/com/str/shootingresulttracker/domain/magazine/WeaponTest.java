package com.str.shootingresulttracker.domain.magazine;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeaponTest extends AbstractUnitTest {

    @Test
    @DisplayName("when weapon is created then creation date should be set based on provided clock")
    void whenWeaponIsCreatedThenCreationDateShouldBeSetBasedOnProvidedClock() {
        //given
        var defaultTime = setClock(2018, 6, 5);
        UUID createdBy = UUID.randomUUID();

        //when
        Weapon weapon = new Weapon(
                "Test",
                WeaponType.PISTOL,
                Caliber.ACP_45,
                "Test model",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                createdBy,
                clock
        );

        //then
        assertEquals(defaultTime, weapon.getCreationDate());
    }

    @Test
    @DisplayName("weapon can be created without production and purchase date")
    void weaponCanBeCreatedWithoutProductionAndPurchaseDate() {
        //given
        var defaultTime = setClock(2018, 6, 5);
        UUID createdBy = UUID.randomUUID();

        //then
        Assertions.assertDoesNotThrow(() ->
                new Weapon(
                        "Test",
                        WeaponType.PISTOL,
                        Caliber.ACP_45,
                        "Test model",
                        null,
                        null,
                        createdBy,
                        clock
                ));
    }
}
