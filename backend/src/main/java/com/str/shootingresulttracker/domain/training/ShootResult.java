package com.str.shootingresulttracker.domain.training;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShootResult {

    private int score;
    private BulletHole bulletHole;

    public ShootResult(int score, BulletHole bulletHole) {
        this.score = score;
        this.bulletHole = bulletHole;
    }
}
