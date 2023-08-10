package com.str.shootingresulttracker.provider;

import com.str.shootingresulttracker.weapon.Caliber;
import com.str.shootingresulttracker.weapon.Weapon;
import com.str.shootingresulttracker.weapon.WeaponType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import spock.util.time.MutableClock;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeaponTestProvider {

    public static Weapon create() {
        return new Weapon(
                "Weapon" + UUID.randomUUID().toString().substring(0, 10),
                WeaponType.PISTOL,
                Caliber.PARABELLUM_9X19,
                "Glock 17",
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                new MutableClock()
        );
    }
}