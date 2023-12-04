package com.str.shootingresulttracker.domain.training;

import java.time.OffsetDateTime;
import java.util.UUID;

public record TrainingDto(
        UUID id,
        String name,
        OffsetDateTime sessionDate,
        String place,
        int resultCount
) {
}
