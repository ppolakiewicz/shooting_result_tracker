package com.str.shootingresulttracker.domain.training.focus;

import com.str.shootingresulttracker.domain.model.Circle;
import com.str.shootingresulttracker.domain.model.Distance;
import com.str.shootingresulttracker.domain.training.ReferenceScale;
import com.str.shootingresulttracker.domain.training.ShootResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collection;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MRadFocus {

    private static final double ONE_MRAD_RADIUS_ON_100M = 0.100104d;
    private final double value;

    public static MRadFocus zero() {
        return new MRadFocus(0d);
    }

    public static MRadFocus withValue(double focus) {
        return new MRadFocus(focus);
    }

    public static MRadFocus calculate(Distance distance, ReferenceScale referenceScale, Collection<ShootResult> shootResults) {
        requireNonNull(distance, "Distance can not be empty");
        requireNonNull(referenceScale, "Reference scale can not be empty");
        requireNonNull(shootResults, "Shoot results can not be empty");

        if (shootResults.isEmpty()) {
            return zero();
        }

        var shootsAsCircles = shootResults.stream()
                .map(ShootResult::getBulletHole)
                .filter(Optional::isPresent)
                .map(bulletHole -> bulletHole.get().getAsCircle())
                .map(Circle::center)
                .toList();
        Circle resultCircle = MinimumEnclosingCircle.calculate(shootsAsCircles);

        var circleRadiusInMeters = (resultCircle.radius() * referenceScale.distance().getInMeters()) / referenceScale.pictureLength();
        var oneMoaForDistance = (distance.getInMeters() / 100d) * ONE_MRAD_RADIUS_ON_100M;
        return new MRadFocus(circleRadiusInMeters / oneMoaForDistance);
    }
}
