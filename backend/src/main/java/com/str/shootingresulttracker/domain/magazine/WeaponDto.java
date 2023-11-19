package com.str.shootingresulttracker.domain.magazine;

import java.time.OffsetDateTime;
import java.util.UUID;

public record WeaponDto(
        UUID id,
        String name,
        WeaponType type,
        Caliber caliber,
        String model,
        OffsetDateTime productionDate,
        OffsetDateTime purchaseDate,
        OffsetDateTime creationDate
) {
}
