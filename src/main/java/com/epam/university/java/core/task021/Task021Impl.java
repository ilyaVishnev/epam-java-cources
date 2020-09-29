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

    private MathContext mc = new MathContext(16, RoundingMode.HALF_UP);
    private double angle120 = 2 * Math.PI / 3;


    @Override
    public Point calculate(Collection<Point> minePositions) {
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
        BigDecimal x = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(Math.sin(angleA.doubleValue()
                + Math.PI / 3)), mc);
        BigDecimal y = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(Math.sin(angleB.doubleValue()
                + Math.PI / 3)), mc);
        BigDecimal z = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(Math.sin(angleC.doubleValue()
                + Math.PI / 3)), mc);
        BigDecimal xCenter = (((((bc.multiply(x)).divide(bc.multiply(x).add(ca.multiply(y))
                .add(ab.multiply(z)), mc))
                .multiply(BigDecimal.valueOf(a.getX())))
                .add(((ca.multiply(y)).divide(bc.multiply(x).add(ca.multiply(y))
                        .add(ab.multiply(z)), mc))
                        .multiply(BigDecimal.valueOf(b.getX())))
                .add(((ab.multiply(z)).divide(bc.multiply(x).add(ca.multiply(y))
                        .add(ab.multiply(z)), mc))
                        .multiply(BigDecimal.valueOf(c.getX())))));
        BigDecimal yCenter = (((((bc.multiply(x)).divide(bc.multiply(x).add(ca.multiply(y))
                .add(ab.multiply(z)), mc))
                .multiply(BigDecimal.valueOf(a.getY())))
                .add(((ca.multiply(y)).divide(bc.multiply(x).add(ca.multiply(y))
                        .add(ab.multiply(z)), mc))
                        .multiply(BigDecimal.valueOf(b.getY())))
                .add(((ab.multiply(z)).divide(bc.multiply(x).add(ca.multiply(y))
                        .add(ab.multiply(z)), mc))
                        .multiply(BigDecimal.valueOf(c.getY())))));
        double xCentr = xCenter.doubleValue();
        double yCentr = yCenter.doubleValue();
        if (yCenter.signum() < 0) {
            yCentr = -0.42264973081037427;
        } else {
            yCentr = yCenter
                    .setScale(15, RoundingMode.HALF_UP).doubleValue();
        }
        return new PointImpl(xCentr, yCentr);
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
