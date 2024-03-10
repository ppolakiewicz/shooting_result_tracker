package com.str.shootingresulttracker.domain.training.focus;

import com.str.shootingresulttracker.domain.model.Distance;
import com.str.shootingresulttracker.domain.training.BulletHole;
import com.str.shootingresulttracker.domain.training.ReferenceScale;
import com.str.shootingresulttracker.domain.training.ShootResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoaFocusTest {


    @Test
    @DisplayName("Should properly calculate moa focus")
    void shouldProperlyCalculateMoaFocus() {
        //given
        var shootingDistance = Distance.ofMeters(100d);
        var referenceScale = new ReferenceScale(Distance.ofMeters(0.1d), 100d);

        //and: shooting results
        var firstShoot = ShootResult.fullResult(0, new BulletHole(1, 1, 0.5d));
        var secondShoot = ShootResult.fullResult(0, new BulletHole(2, 2, 0.5d));

        //and: expected result
        var expectedMoa = MoaFocus.withValue(0.02430854056401843d);

        //when
        var result = MoaFocus.calculate(shootingDistance, referenceScale, List.of(firstShoot, secondShoot));

        //then
        assertEquals(expectedMoa.getValue(), result.getValue());
    }

}