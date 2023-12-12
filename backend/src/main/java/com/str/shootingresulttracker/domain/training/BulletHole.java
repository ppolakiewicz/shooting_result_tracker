package com.str.shootingresulttracker.domain.training;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BulletHole {

    private final Point center;
    private final float radius;

    public BulletHole(int x, int y, float radius) {
        this.center = new Point(x, y);
        this.radius = radius;
    }

    public record Point(
            int x,
            int y
    ) {
    }

}
