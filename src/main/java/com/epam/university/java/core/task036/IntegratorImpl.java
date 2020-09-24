package com.epam.university.java.core.task036;

import java.util.function.Function;

public class IntegratorImpl implements Integrator {
    @Override
    public double integrate(double left, double right,
                            Function<Double, Double> function) {
        double area = 0;
        double h = 0.0001;
        for (int i = 0; i < (right - left) / h; i++) {
            area += 0.5 * h * (function.apply(left + i * h)
                    + function.apply(left + (i + 1) * h));
        }
        return area;
    }
}
