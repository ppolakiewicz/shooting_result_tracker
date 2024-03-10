package com.str.shootingresulttracker.domain.training.focus;

import com.str.shootingresulttracker.domain.model.Circle;
import com.str.shootingresulttracker.domain.model.Point;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class that calculates minimum enclosing circle
 * <a href="https://www.geeksforgeeks.org/minimum-enclosing-circle-using-welzls-algorithm/">Source</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MinimumEnclosingCircle {


    // Function to find the minimum enclosing circle for N integer points in a 2-D plane
    static Circle calculate(Collection<Point> points) {
        List<Point> pointsCopy = new ArrayList<>(points);
        Collections.shuffle(pointsCopy);
        return welzlHelper(pointsCopy, new ArrayList<>(), pointsCopy.size());
    }

    // Function to return the Euclidean distance between two points
    private static double dist(Point firstPoint, Point secondPoint) {
        return Math.sqrt(Math.pow(firstPoint.x() - secondPoint.x(), 2) + Math.pow(firstPoint.y() - secondPoint.y(), 2));
    }

    // Function to check whether a point lies inside or on the boundaries of the circle
    private static boolean isInside(Circle circle, Point point) {
        return dist(circle.center(), point) <= circle.radius();
    }

    // Helper method to get a circle defined by 3 points
    private static Point getCircleCenter(double bx, double by, double cx, double cy) {
        double B = bx * bx + by * by;
        double C = cx * cx + cy * cy;
        double D = bx * cy - by * cx;
        return new Point((cy * B - by * C) / (2 * D), (bx * C - cx * B) / (2 * D));
    }

    // Function to return a unique circle that intersects three points
    private static Circle circleFrom(Point firstPoint, Point secondPoint, Point thirdPoint) {
        Point I = getCircleCenter(secondPoint.x() - firstPoint.x(), secondPoint.x() - firstPoint.y(), thirdPoint.x() - firstPoint.x(), thirdPoint.y() - firstPoint.y());
        Point center = new Point(I.x() + firstPoint.x(), I.y() + firstPoint.y());
        return new Circle(center, dist(I, firstPoint));
    }

    // Function to return the smallest circle that intersects 2 points
    private static Circle circleFrom(Point firstPoint, Point secondPoint) {
        Point C = new Point((firstPoint.x() + secondPoint.x()) / 2.0, (firstPoint.y() + secondPoint.y()) / 2.0);
        return new Circle(C, dist(firstPoint, secondPoint) / 2.0);
    }

    // Function to check whether a circle encloses the given points
    private static boolean isValidCircle(Circle circle, List<Point> points) {
        // Iterating through all the points to check whether the points lie inside the circle or not
        for (Point p : points) {
            if (!isInside(circle, p)) {
                return false;
            }
        }
        return true;
    }

    // Function to return the minimum enclosing circle for N <= 3
    private static Circle minCircleTrivial(List<Point> points) {
        assert points.size() <= 3;
        if (points.isEmpty()) {
            return new Circle(new Point(0, 0), 0);
        } else if (points.size() == 1) {
            return new Circle(points.get(0), 0);
        } else if (points.size() == 2) {
            return circleFrom(points.get(0), points.get(1));
        }

        // To check if MEC can be determined by 2 points only
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                Circle c = circleFrom(points.get(i), points.get(j));
                if (isValidCircle(c, points)) {
                    return c;
                }
            }
        }
        return circleFrom(points.get(0), points.get(1), points.get(2));
    }

    // Returns the MEC using Welzl's algorithm
    // Takes a set of input points P and a set R
    // points on the circle boundary.
    // n represents the number of points in P that are not yet processed.
    private static Circle welzlHelper(List<Point> points, List<Point> processedPoints, int n) {
        // Base case when all points processed or |R| = 3
        if (n == 0 || processedPoints.size() == 3) {
            return minCircleTrivial(processedPoints);
        }

        // Pick a random point randomly
        int idx = (int) (Math.random() * n);
        Point p = points.get(idx);

        // Put the picked point at the end of P since it's more efficient than
        // deleting from the middle of the list
        Collections.swap(points, idx, n - 1);

        // Get the MEC circle d from the set of points P - {p}
        Circle d = welzlHelper(points, processedPoints, n - 1);

        // If d contains p, return d
        if (isInside(d, p)) {
            return d;
        }

        // Otherwise, must be on the boundary of the MEC
        processedPoints.add(p);

        // Return the MEC for P - {p} and R U {p}
        return welzlHelper(points, processedPoints, n - 1);
    }
}
