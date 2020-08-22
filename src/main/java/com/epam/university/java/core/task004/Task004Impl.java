package com.epam.university.java.core.task004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task004Impl implements Task004 {

    /**
     * Find intersection of two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return array of common elements
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] intersection(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        List<String> result = new ArrayList<String>();
        List<String> firstList = Arrays.asList(first);
        for (int i = 0; i < second.length; i++) {
            if (firstList.contains(second[i])) {
                result.add(second[i]);
            }
        }
        return result.toArray(String[]::new);
    }

    /**
     * Find union of two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return array of all elements of array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] union(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        List<String> secondList = new ArrayList<>(Arrays.asList(second));
        secondList.removeAll(Arrays.asList(first));
        String[] secResult = secondList.toArray(String[]::new);
        String[] result = new String[first.length + secResult.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(secResult, 0, result, first.length, secResult.length);
        return result;
    }
}
