package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class TrainingInMemoryRepository extends AbstractInMemoryRepository<Training> implements TrainingRepository {

    @Override
    public List<TrainingDto> queryAll(UUID ownerId) {
        return collection.values().stream()
                .filter(training -> training.getCreatedBy() == ownerId)
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<TrainingDto> queryById(UUID trainingId, UUID ownerId) {
        return load(trainingId, ownerId).map(this::toDto);
    }

    @Override
    public Optional<Training> load(UUID trainingId, UUID ownerId) {
        return collection.values().stream()
                .filter(training -> training.getId() == trainingId)
                .filter(training -> training.getCreatedBy() == ownerId)
                .findFirst();
    }

    private TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getName(),
                training.getSessionDate(),
                training.getPlace(),
                training.getResultCount()
        );
    }
}
