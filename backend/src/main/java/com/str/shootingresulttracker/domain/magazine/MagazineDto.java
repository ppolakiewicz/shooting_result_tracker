package com.str.shootingresulttracker.domain.magazine;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public record MagazineDto(
        UUID id,
        String name,
        int capacity,
        int weaponCount,
        Set<Ammunition> ammunition,
        OffsetDateTime creationDate
) {
}
