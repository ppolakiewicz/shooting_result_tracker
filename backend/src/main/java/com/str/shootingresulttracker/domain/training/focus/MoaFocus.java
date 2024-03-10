package com.str.shootingresulttracker.domain.training.focus;

import com.str.shootingresulttracker.domain.kernel.Circle;
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
public class MoaFocus {

    // 1 MOA = 0,0291m / 100m
    private static final double ONE_MOA_RADIUS_ON_100M = 0.02908882083333333d;
    private final double value;

    public static MoaFocus zero() {
        return new MoaFocus(0d);
    }

    public static MoaFocus withValue(double focus) {
        return new MoaFocus(focus);
    }

    public static MoaFocus calculate(Distance distance, ReferenceScale referenceScale, Collection<ShootResult> shootResults) {
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
        var oneMoaForDistance = (distance.getInMeters() / 100d) * ONE_MOA_RADIUS_ON_100M;
        return new MoaFocus(circleRadiusInMeters / oneMoaForDistance);
    }

}
