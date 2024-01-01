package com.str.shootingresulttracker.domain.training;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShootResult {

    @Getter
    private int score;
    private BulletHole bulletHole;

    private ShootResult(int score, BulletHole bulletHole) {
        this.score = score;
        this.bulletHole = bulletHole;
    }

    public static ShootResult fullResult(int score, BulletHole bulletHole) {
        Objects.requireNonNull(bulletHole, "Bullet hole can not be empty");
        return new ShootResult(score, bulletHole);
    }

    public static ShootResult simpleResult(int score) {
        return new ShootResult(score, null);
    }

    public Optional<BulletHole> getBulletHole() {
        return Optional.ofNullable(bulletHole);
    }

}
