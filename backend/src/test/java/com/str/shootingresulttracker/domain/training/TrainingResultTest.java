package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import com.str.shootingresulttracker.domain.model.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingResultTest extends AbstractUnitTest {

    @Test
    @DisplayName("Result should be created when all data is valid")
    void resultShouldBeCreatedWhenAllDataIsValid() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";

        //expect
        assertDoesNotThrow(() -> new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1)));
    }

    @Test
    @DisplayName("Result weapon can be changed")
    void resultWeaponCanBeChanged() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1));

        //and
        var newWeaponId = UUID.randomUUID();
        var newWeaponName = "Other test weapon";

        //when
        trainingResult.changeWeapon(newWeaponId, newWeaponName);

        //then
        assertEquals(newWeaponId, trainingResult.getWeaponId());
        assertEquals(newWeaponName, trainingResult.getWeaponName());
    }

    @Test
    @DisplayName("Shots results can be set and modified")
    void shotsResultsCanBeSetAndModified() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1));

        //and
        List<ShootResult> firstShotResults = of(
                new ShootResult(1, new BulletHole(0, 0, 2)),
                new ShootResult(2, new BulletHole(2, 2, 2))
        );
        List<ShootResult> secondShotResults = of(
                new ShootResult(10, new BulletHole(100, 100, 2)),
                new ShootResult(10, new BulletHole(99, 99, 2))
        );

        //expect
        trainingResult.setShotsResults(firstShotResults);
        assertEquals(firstShotResults, trainingResult.getShotsResults());

        //and
        trainingResult.setShotsResults(secondShotResults);
        assertEquals(secondShotResults, trainingResult.getShotsResults());
    }

}