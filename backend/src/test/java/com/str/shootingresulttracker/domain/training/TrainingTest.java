package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import com.str.shootingresulttracker.domain.model.Distance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrainingTest extends AbstractUnitTest {

    @Test
    @DisplayName("Training should be created")
    void trainingShouldBeCreated() {
        //given
        UUID createdBy = UUID.randomUUID();
        String trainingName = "Test training";
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test zone";

        //expect
        Assertions.assertDoesNotThrow(() -> new Training(clock, createdBy, trainingName, sessionDate, place));
    }

    @Test
    @DisplayName("Training can not be created without name")
    void trainingCanNotBeCreatedWithoutName() {
        //given
        UUID createdBy = UUID.randomUUID();
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test zone";

        String trainingName = null;

        //expect
        assertThrows(IllegalArgumentException.class, () -> new Training(clock, createdBy, trainingName, sessionDate, place));
    }


    @Test
    @DisplayName("Training can not be created without session date")
    void trainingCanNotBeCreatedWithoutSessionDate() {
        //given
        UUID createdBy = UUID.randomUUID();
        String trainingName = "Test training";
        OffsetDateTime sessionDate = null;
        String place = "Test zone";


        //expect
        assertThrows(NullPointerException.class, () -> new Training(clock, createdBy, trainingName, sessionDate, place));
    }

    @Test
    @DisplayName("Training should accept training result")
    void trainingShouldAcceptTrainingResult() {
        //given: training result
        UUID createdBy = UUID.randomUUID();
        UUID weaponId = UUID.randomUUID();
        String weaponName = "Test weapon";

        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1));

        //and: training
        String trainingName = "Test training";
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test zone";

        var training = new Training(clock, createdBy, trainingName, sessionDate, place);

        //when
        var result = training.addResult(trainingResult);

        //then
        assertTrue(result.isSuccess());
    }

    @Test
    @DisplayName("Training should not accept more results than limit")
    void trainingShouldNotAcceptMoreResultsThanLimit() {
        //given
        UUID createdBy = UUID.randomUUID();
        String trainingName = "Test training";
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test zone";

        var training = new Training(clock, createdBy, trainingName, sessionDate, place);

        //and: fill training to limit
        UUID weaponId = UUID.randomUUID();
        String weaponName = "Test weapon";

        for (int i = 0; i < 5; i++) {
            var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1));
            training.addResult(trainingResult);
        }

        //when
        var nextTrainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, of(), Distance.ofMeters(1));
        var result = training.addResult(nextTrainingResult);

        //then
        assertTrue(result.isFail());
    }

}