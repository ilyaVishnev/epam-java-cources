package com.epam.university.java.core.task005;

public class Task005Impl implements Task005 {

    /**
     * Find two numbers, quotient of which will be closest to PI number. Ex. if digit is 1,
     * holder values should be between 1 and 9, if digits is equals to 2, values should
     * be between 10 and 99 and so on.
     * <p>
     * Tip: Math.abs((a / b) - Math.PI) -> min
     * </p>
     *
     * @param digits amount of digits in numbers.
     * @return holder which contains both numbers
     * @throws IllegalArgumentException if digits less or equals to the zero, or more than ten
     */
    @Override
    public PiHolder findPi(int digits) {
        if (digits <= 0 || digits > 10) {
            throw new IllegalArgumentException();
        }
        int denominator = 0;
        int numerator = 0;
        double min = Double.MAX_VALUE;
        for (int i = (int) Math.pow(10, digits - 1); i <= (int) 4 * Math.pow(10, digits - 1); i++) {
            int k = 3 * i;
            int l = 4 * i;
            for (int j = k; j < Math.pow(10, digits) && j < l; j++) {
                double myMin = Math.abs((double) j / i - Math.PI);
                if (myMin < min) {
                    numerator = j;
                    denominator = i;
                    min = myMin;
                }
            }
        }
        PiHolderImpl piHolder = new PiHolderImpl();
        piHolder.setFirst(numerator);
        piHolder.setSecond(denominator);
        return piHolder;
    }
}
