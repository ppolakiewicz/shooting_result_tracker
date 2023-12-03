package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainingResultTest extends AbstractUnitTest {

    @Test
    @DisplayName("Result should be created when all data is valid")
    void resultShouldBeCreatedWhenAllDataIsValid() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";

        //expect
        assertDoesNotThrow(() -> new TrainingResult(clock, createdBy, weaponId, weaponName));
    }

    @Test
    @DisplayName("Result weapon can be changed")
    void resultWeaponCanBeChanged() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName);

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
    @DisplayName("Result should store just unique file ids")
    void resultShouldStoreJustUniqueFileIds() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName);

        //and
        var firstFileId = 1L;
        var secondFileId = 2L;

        //when
        trainingResult.addFile(firstFileId);
        trainingResult.addFile(firstFileId);

        trainingResult.addFile(secondFileId);
        trainingResult.addFile(secondFileId);

        //then
        var trainingResultFiles = trainingResult.getFilesIds();
        assertEquals(2, trainingResultFiles.size());
        assertTrue(trainingResultFiles.contains(firstFileId));
        assertTrue(trainingResultFiles.contains(secondFileId));
    }

    @Test
    @DisplayName("Shots results can be set and modified")
    void shotsResultsCanBeSetAndModified() {
        //given
        var createdBy = UUID.randomUUID();
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName);

        //and
        List<Integer> firstShotResults = List.of(1, 2, 3, 4, 5);
        List<Integer> secondShotResults = List.of(0, 0, 0, 9);

        //expect
        trainingResult.setShotsResults(firstShotResults);
        assertEquals(firstShotResults, trainingResult.getShotsResults());

        //and
        trainingResult.setShotsResults(secondShotResults);
        assertEquals(secondShotResults, trainingResult.getShotsResults());
    }

}