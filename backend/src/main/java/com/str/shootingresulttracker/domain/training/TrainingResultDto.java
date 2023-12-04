package com.str.shootingresulttracker.domain.training;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TrainingResultDto(
        UUID id,
        UUID weaponId,
        String weaponName,
        List<Integer> shotsResults,
        Set<Long> filesIds
) {
}
