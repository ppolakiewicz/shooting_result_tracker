package com.str.shootingresulttracker.web.training;

import com.str.shootingresulttracker.domain.model.Distance;
import com.str.shootingresulttracker.domain.training.ShootResult;

import java.util.List;
import java.util.UUID;

record TrainingResultCreateCommand(
        UUID weaponId,
        List<ShootResult> shootResults,
        Distance distance
) {
}
