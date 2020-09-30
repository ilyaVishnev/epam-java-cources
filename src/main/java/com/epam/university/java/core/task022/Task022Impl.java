package com.epam.university.java.core.task022;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task022Impl implements Task022 {
    /**
     * Given collection of n integers, find the maximum value that can be calculated
     * by summing (n-1) integers.
     *
     * @param numbers collection of numbers
     * @return maximum value
     */
    @Override
    public int maxSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Integer> integers = new ArrayList<>(numbers);
        Integer max = integers.stream().mapToInt(i -> i.intValue()).sum();
        Integer min = Integer.MAX_VALUE;
        for (Integer i : integers) {
            if (i < min) {
                min = i;
            }
        }
        return max - min;
    }

    /**
     * Given collection of n integer, find the minimum value that can ba calculated
     * by summing (n-1) integers.
     *
     * @param numbers collection of numbers
     * @return minimum value
     */
    @Override
    public int minSum(Collection<Integer> numbers) {
        List<Integer> integers = new ArrayList<>(numbers);
        Integer min = integers.stream().mapToInt(i -> i.intValue()).sum();
        Integer max = Integer.MIN_VALUE;
        for (Integer i : integers) {
            if (i > max) {
                max = i;
            }
        }
        return min - max;
    }
}
