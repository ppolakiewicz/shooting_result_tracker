package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractIntegrationTest;
import com.str.shootingresulttracker.domain.model.Distance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainingServiceIT extends AbstractIntegrationTest {

    @Autowired
    private TrainingService service;

    @Test
    @DisplayName("Should add training result to training")
    void shouldAddTrainingResultToTraining() {
        //given: existing training
        String name = "Test name";
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test place";
        UUID ownerId = UUID.randomUUID();
        var trainingId = service.create(name, sessionDate, place, ownerId);

        assertTrue(trainingId.isValue());

        //when: add training result
        var weaponId = UUID.randomUUID();
        var weaponName = "Test weapon";

        var result = service.addSimpleTrainingResult(
                trainingId.getValue(),
                weaponId,
                weaponName,
                ownerId,
                List.of(),
                Distance.ofMeters(1)
        );

        //then: result should be true
        assertTrue(result.isSuccess());

        //and: results should be return in query
        var trainingResults = service.queryTrainingResults(trainingId.getValue(), ownerId);
        assertFalse(trainingResults.isEmpty());
        assertEquals(1, trainingResults.size());

        var contains = trainingResults.stream()
                .anyMatch(trainingResult -> trainingResult.weaponId().equals(weaponId) && trainingResult.weaponName().equals(weaponName));
        assertTrue(contains);
    }

}
