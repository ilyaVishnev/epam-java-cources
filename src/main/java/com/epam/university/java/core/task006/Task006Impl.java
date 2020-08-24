package com.epam.university.java.core.task006;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task006Impl implements Task006 {

    /**
     * Calculate resistance by collection of measurements using Least Square method.
     *
     * @param measurements collection of measurements
     * @return value of resistance
     * @throws IllegalArgumentException if measurements not provided
     */
    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        }
        double amper = 0;
        double voltage = 0;
        double amperSq = 0;
        double amperVoltage = 0;
        for (Measurement measurement : measurements) {
            amper += measurement.getAmperage();
            voltage += measurement.getVoltage();
            amperSq += Math.pow(measurement.getAmperage(), 2);
            amperVoltage += measurement.getVoltage() * measurement.getAmperage();
        }
        double result = (double) Math.round(((amper * voltage - measurements.size()
                * amperVoltage)
                / (Math.pow(amper, 2) - measurements.size() * amperSq)) * 1000) / 1000;
        return Double.isNaN(result) ? 0.0 : result;
    }
}
