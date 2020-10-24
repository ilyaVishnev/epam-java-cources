package com.epam.university.java.core.task047;

import java.util.Arrays;
import java.util.stream.Stream;

public class Task047Impl implements Task047 {

    private int[] counts;

    @Override
    public long calculateInversions(int n, int[] a) {
        counts = new int[n];
        Arrays.fill(counts, 0);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    counts[j]++;
                }
            }
        }
        return Arrays.stream(counts).reduce(0, (x, y) -> x + y);
    }
}
