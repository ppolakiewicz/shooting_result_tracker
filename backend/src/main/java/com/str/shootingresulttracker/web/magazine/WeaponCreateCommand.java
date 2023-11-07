package com.str.shootingresulttracker.web.magazine;

import com.str.shootingresulttracker.domain.magazine.Caliber;
import com.str.shootingresulttracker.domain.magazine.WeaponType;

import java.time.OffsetDateTime;

record WeaponCreateCommand(
        String weaponName,
        WeaponType type,
        Caliber caliber,
        String model,
        OffsetDateTime productionDate,
        OffsetDateTime purchaseDate
) {
}
