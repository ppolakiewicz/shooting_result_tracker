package com.str.shootingresulttracker.domain.provider;

import com.str.shootingresulttracker.domain.magazine.Caliber;
import com.str.shootingresulttracker.domain.magazine.Weapon;
import com.str.shootingresulttracker.domain.magazine.WeaponType;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

public class WeaponTestProvider {

    public static Weapon create(Clock clock) {
        return new Weapon(
                "Weapon" + UUID.randomUUID().toString().substring(0, 10),
                WeaponType.PISTOL,
                Caliber.PARABELLUM_9X19,
                "Glock 17",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                UUID.randomUUID(),
                clock
        );
    }
}
