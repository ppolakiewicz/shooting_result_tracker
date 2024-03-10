package com.str.shootingresulttracker.domain.training;

import com.str.shootingresulttracker.domain.model.Circle;
import com.str.shootingresulttracker.domain.model.Point;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BulletHole {

    private final Circle circle;

    public BulletHole(int x, int y, double radius) {
        this.circle = new Circle(new Point(x, y), radius);
    }

    public Circle getAsCircle() {
        return circle;
    }

}
