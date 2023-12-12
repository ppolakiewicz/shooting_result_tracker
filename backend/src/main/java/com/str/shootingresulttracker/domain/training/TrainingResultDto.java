package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.model.Distance;

import java.util.List;
import java.util.UUID;

public record TrainingResultDto(
        UUID id,
        UUID weaponId,
        String weaponName,
        List<ShootResult> shotsResults,
        Distance distance
) {
}
