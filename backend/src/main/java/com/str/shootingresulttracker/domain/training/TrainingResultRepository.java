package com.str.shootingresulttracker.domain.training;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

interface TrainingResultRepository extends Repository<TrainingResult, UUID> {

    @Query("""
            select new com.str.shootingresulttracker.domain.training.TrainingResultDto(
            tr.id,
            tr.weaponId,
            tr.weaponName,
            tr.shotsResults,
            tr.filesIds
            )
            from TrainingResult tr
            where tr.createdBy = :ownerId
            and tr.training.id = :trainingId
            """)
    List<TrainingResultDto> queryAll(UUID trainingId, UUID ownerId);

}
