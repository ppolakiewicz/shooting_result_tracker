package com.str.shootingresulttracker.domain.training.focus;

import com.str.shootingresulttracker.domain.model.Circle;
import com.str.shootingresulttracker.domain.model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class MinimumEnclosingCircleTest {


    static Stream<Arguments> pointsList() {
        return Stream.of(
                arguments(List.of(
                                new Point(0, 0),
                                new Point(0, 1),
                                new Point(1, 0)
                        ),
                        new Circle(new Point(0.5, 0.5), 0.7071067811865476d)
                ),
                arguments(List.of(
                                new Point(2, 2),
                                new Point(-2, 2),
                                new Point(-2, -2),
                                new Point(2, -2)
                        ),
                        new Circle(new Point(0, 0), 2.8284271247461903d)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("pointsList")
    @DisplayName("Should properly calculate minimum enclosing circle")
    void shouldProperlyCalculateMinimumEnclosingCircle(List<Point> points, Circle expectedResult) {
        //when
        var result = MinimumEnclosingCircle.calculate(points);

        //then
        Assertions.assertEquals(expectedResult, result);
    }
}