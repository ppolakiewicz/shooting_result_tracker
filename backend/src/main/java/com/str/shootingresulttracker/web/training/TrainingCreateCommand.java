package com.str.shootingresulttracker.web.training;

import java.time.OffsetDateTime;

record TrainingCreateCommand(
        String name,
        OffsetDateTime sessionDate,
        String place
) {
}
