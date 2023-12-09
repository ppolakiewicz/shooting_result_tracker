package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.core.AbstractInMemoryRepository;

import java.util.List;
import java.util.UUID;

class TrainingResultInMemoryRepository extends AbstractInMemoryRepository<TrainingResult> implements TrainingResultRepository {

    @Override
    public List<TrainingResultDto> queryAll(UUID trainingId, UUID ownerId) {
        return collection.values().stream()
                .filter(result -> result.getCreatedBy() == ownerId)
                .map(this::toDto)
                .toList();
    }

    private TrainingResultDto toDto(TrainingResult result) {
        return new TrainingResultDto(
                result.getId(),
                result.getWeaponId(),
                result.getWeaponName(),
                result.getShotsResults(),
                result.getFilesIds()
        );
    }
}
