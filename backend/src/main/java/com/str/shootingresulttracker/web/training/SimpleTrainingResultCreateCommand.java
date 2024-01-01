package com.str.shootingresulttracker.web.training;

import com.str.shootingresulttracker.domain.model.Distance;

import java.util.List;
import java.util.UUID;

record SimpleTrainingResultCreateCommand(
        UUID weaponId,
        List<Integer> shootResults,
        Distance distance
) {
}
