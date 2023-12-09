package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrainingServiceTest extends AbstractUnitTest {

    private TrainingService service;

    @BeforeEach
    void setUp() {
        TrainingRepository trainingRepository = new TrainingInMemoryRepository();
        TrainingResultRepository resultRepository = new TrainingResultInMemoryRepository();

        service = new TrainingService(trainingRepository, resultRepository, clock);
    }

    @Test
    @DisplayName("Should create and save training")
    void shouldCreateAndSaveTraining() {
        //given
        String name = "Test name";
        OffsetDateTime sessionDate = OffsetDateTime.now();
        String place = "Test place";
        UUID ownerId = UUID.randomUUID();

        //when
        var result = service.create(name, sessionDate, place, ownerId);

        //then: successfully created training
        assertTrue(result.isValue());

        //and
        var training = service.queryById(result.getValue(), ownerId);
        assertTrue(training.isPresent());
        assertEquals(name, training.get().name());
    }
}