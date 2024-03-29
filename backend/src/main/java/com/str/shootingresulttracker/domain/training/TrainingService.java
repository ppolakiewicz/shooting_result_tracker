package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.model.BooleanResult;
import com.str.shootingresulttracker.domain.model.Distance;
import com.str.shootingresulttracker.domain.model.DomainResult;
import com.str.shootingresulttracker.domain.model.EntityDoNotExistsException;
import com.str.shootingresulttracker.domain.training.error.FullTrainingError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;
    private final TrainingResultRepository resultRepository;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<TrainingDto> queryAll(UUID ownerId) {
        return repository.queryAll(ownerId);
    }

    @Transactional(readOnly = true)
    public Optional<TrainingDto> queryById(UUID trainingId, UUID ownerId) {
        return repository.queryById(trainingId, ownerId);
    }

    @Transactional(readOnly = true)
    public List<TrainingResultDto> queryTrainingResults(UUID trainingId, UUID ownerId) {
        return resultRepository.queryAll(trainingId, ownerId);
    }

    @Transactional
    public DomainResult<UUID> create(String name, OffsetDateTime sessionDate, String place, UUID ownerId) {
        var training = new Training(clock, ownerId, name, sessionDate, place);
        training = repository.save(training);
        return new DomainResult<>(training.getId());
    }

    @Transactional
    public BooleanResult<FullTrainingError> addSimpleTrainingResult(
            UUID trainingId,
            UUID weaponId,
            String weaponName,
            UUID createdBy,
            List<Integer> shootResults,
            Distance distance) {

        var training = loadTraining(trainingId, createdBy);
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, shootResults, distance);

        var result = training.addResult(trainingResult);
        if (result.isSuccess()) {
            repository.save(training);
        }
        return result;
    }

    @Transactional
    public BooleanResult<FullTrainingError> addFullTrainingResult(
            UUID trainingId,
            UUID weaponId,
            String weaponName,
            UUID createdBy,
            List<ShootResult> shootResults,
            ReferenceScale referenceScale,
            Distance distance) {

        var training = loadTraining(trainingId, createdBy);
        var trainingResult = new TrainingResult(clock, createdBy, weaponId, weaponName, distance, shootResults, referenceScale);

        var result = training.addResult(trainingResult);
        if (result.isSuccess()) {
            repository.save(training);
        }
        return result;
    }

    private Training loadTraining(UUID trainingId, UUID ownerId) {
        return repository.load(trainingId, ownerId).orElseThrow(EntityDoNotExistsException::new);
    }

}
