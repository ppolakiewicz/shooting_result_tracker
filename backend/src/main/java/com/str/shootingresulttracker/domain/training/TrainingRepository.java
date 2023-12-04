package com.str.shootingresulttracker.domain.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface TrainingRepository extends JpaRepository<Training, UUID> {

    @Query("""
            select new com.str.shootingresulttracker.domain.training.TrainingDto(
            t.id,
            t.name,
            t.sessionDate,
            t.place,
            t.resultCount
            )
            from Training t
            where t.createdBy = :ownerId
            """)
    List<TrainingDto> queryAll(UUID ownerId);


    @Query("""
            select t
            from Training t
            where t.id = :trainingId
            and t.createdBy = :ownerId
            """)
    Optional<Training> load(UUID trainingId, UUID ownerId);

    @Query("""
            select new com.str.shootingresulttracker.domain.training.TrainingDto(
            t.id,
            t.name,
            t.sessionDate,
            t.place,
            t.resultCount
            )
            from Training t
            where t.id = :trainingId
            and t.createdBy = :ownerId
            """)
    Optional<TrainingDto> queryById(UUID trainingId, UUID ownerId);
}
