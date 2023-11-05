package com.str.shootingresulttracker.domain.provider

import com.str.shootingresulttracker.domain.magazine.Caliber
import com.str.shootingresulttracker.domain.magazine.Weapon
import com.str.shootingresulttracker.domain.magazine.WeaponType
import lombok.AccessLevel
import lombok.NoArgsConstructor
import spock.util.time.MutableClock

import java.time.OffsetDateTime

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class WeaponTestProvider {

    public static Weapon create() {
        return new Weapon(
                "Weapon" + UUID.randomUUID().toString().substring(0, 10),
                WeaponType.PISTOL,
                Caliber.PARABELLUM_9X19,
                "Glock 17",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                UUID.randomUUID(),
                new MutableClock()
        );
    }
}
