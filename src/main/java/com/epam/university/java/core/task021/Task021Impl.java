package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Task021Impl implements Task021 {

    private double angle120 = 2 * Math.PI / 3;


    @Override
    public Point calculate(Collection<Point> minePositions) {
        if (minePositions == null || minePositions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Point> points = new ArrayList<>(minePositions);
        Point a = points.get(0);
        Point b = points.get(1);
        Point c = points.get(2);
        BigDecimal ab = getSideLength(a, b);
        BigDecimal bc = getSideLength(b, c);
        BigDecimal ca = getSideLength(c, a);
        Double angleA = getAngle(ab.doubleValue(), ca.doubleValue(), bc.doubleValue());
        Double angleB = getAngle(bc.doubleValue(), ab.doubleValue(), ca.doubleValue());
        Double angleC = getAngle(bc.doubleValue(), ca.doubleValue(), ab.doubleValue());
        if (Double.compare(angleA, angle120) >= 0) {
            return new PointImpl(a.getX(), a.getY());
        }
        if (Double.compare(angleB, angle120) >= 0) {
            return new PointImpl(b.getX(), b.getY());
        }
        if (Double.compare(angleC, angle120) >= 0) {
            return new PointImpl(c.getX(), c.getY());
        }
        Point outFirst = findOutsidePoint(points.get(0),
                points.get(1), points.get(2));
        Point outSecond = findOutsidePoint(points.get(1),
                points.get(2), points.get(0));

        Point result = getCrossing(points.get(2), outFirst,
                points.get(0), outSecond);

        result.setX(Double.compare(result.getX(), -0.0d) == 0 ? 0 : result.getX());
        result.setY(Double.compare(result.getY(), -0.0d) == 0 ? 0 : result.getY());
        result.setX(Double.parseDouble(
                String.format("%.15f", result.getX()).replace(",", ".")
        ));
        result.setY(Double.parseDouble(
                String.format("%.15f", result.getY()).replace(",", ".")
        ));
        return result;
    }

    private Point findOutsidePoint(Point a, Point b, Point c) {
        double x = 0;
        double y = 0;

        if (a.getX() > b.getX()) {
            Point temp = a;
            a = b;
            b = temp;
        }

        if (Double.compare(a.getX(), b.getX()) == 0) {
            if (a.getY() < b.getY()) {
                Point temp = a;
                a = b;
                b = temp;
            }
        }

        if (c.getX() <= Math.min(a.getX(), b.getX())) {
            x = (a.getX() + b.getX() + (a.getY() - b.getY()) * Math.sqrt(3)) / 2;
            y = (a.getY() + b.getY() + (b.getX() - a.getX()) * Math.sqrt(3)) / 2;
        } else {
            x = (a.getX() + b.getX() - (a.getY() - b.getY()) * Math.sqrt(3)) / 2;
            y = (a.getY() + b.getY() - (b.getX() - a.getX()) * Math.sqrt(3)) / 2;
        }

        return new PointImpl(x, y);
    }

    private Point getCrossing(Point point1, Point point2, Point point3, Point point4) {
        double x = 0;
        double y = 0;
        double a1 = 0;
        double a2 = 0;
        double b1 = 0;
        double b2 = 0;

        if (point2.getX() < point1.getX()) {
            Point tmp = point1;
            point1 = point2;
            point2 = tmp;
        }
        if (point4.getX() < point3.getX()) {
            Point tmp = point3;
            point3 = point4;
            point4 = tmp;
        }

        if (Double.compare(point1.getX(), point2.getX()) == 0) {
            x = point1.getX();
            a2 = (point3.getY() - point4.getY()) / (point3.getX() - point4.getX());
            b2 = point3.getY() - a2 * point3.getX();
            y = a2 * x + b2;
        } else if (Double.compare(point3.getX(), point4.getX()) == 0) {
            x = point3.getX();
            a1 = (point1.getY() - point2.getY()) / (point1.getX() - point2.getX());
            b1 = point1.getY() - a1 * point1.getX();
            y = a1 * x + b1;
        } else {
            a1 = (point1.getY() - point2.getY()) / (point1.getX() - point2.getX());
            a2 = (point3.getY() - point4.getY()) / (point3.getX() - point4.getX());
            b1 = point1.getY() - a1 * point1.getX();
            b2 = point3.getY() - a2 * point3.getX();
            x = (b2 - b1) / (a1 - a2);
            y = a1 * x + b1;
        }

        return new PointImpl(x, y);
    }

    private BigDecimal getSideLength(Point a, Point b) {
        return BigDecimal.valueOf(Math.hypot(a.getX() - b.getX(),
                a.getY() - b.getY()));
    }

    private Double getAngle(double a, double b, double c) {
        return Math.acos(
                (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)
        );
    }

}
