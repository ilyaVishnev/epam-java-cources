package com.epam.university.java.core.task003;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task003Impl implements Task003 {

    /**
     * Invert array.
     *
     * @param source source array
     * @return inverted array
     * @throws IllegalArgumentException if array not provided
     */
    @Override
    public String[] invert(String[] source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        Collections.reverse(Arrays.asList(source));
        return source;
    }

    /**
     * Join two arrays.
     *
     * @param first  first array
     * @param second second array
     * @return new array which contains items from first and second arrays
     * @throws IllegalArgumentException if arrays not provided
     */
    @Override
    public String[] join(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        String[] result = new String[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Find max element in array.
     *
     * @param source source array
     * @return value of maximal element in array
     * @throws IllegalArgumentException if array not provided
     */
    @Override
    public int findMax(int[] source) {
        if (source == null || source.length == 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(source).max().getAsInt();
    }

    /**
     * Filter array elements in accordance with condition.
     *
     * @param source    source array
     * @param condition condition instance
     * @return filtered array
     * @throws IllegalArgumentException if array or condition not provided
     */
    @Override
    public String[] filter(String[] source, FilteringCondition condition) {
        if (source == null || source.length == 0 || condition == null) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(source).filter(x -> condition.isValid(x)).toArray(String[]::new);
    }

    /**
     * Remove elements from source array.
     *
     * @param source   source array
     * @param toRemote elements to remove
     * @return new array without removed elements
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] removeElements(String[] source, String[] toRemote) {
        if (source == null || toRemote.length == 0 || source == null || toRemote.length == 0) {
            throw new IllegalArgumentException();
        }
        List<String> sourceList = new ArrayList<String>(Arrays.asList(source));
        sourceList.removeAll(Arrays.asList(toRemote));
        return sourceList.toArray(String[]::new);
    }

    /**
     * Convert source array in accordance with provided operation.
     *
     * @param source    source array
     * @param operation operation instance
     * @return converted array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] map(String[] source, MappingOperation operation) {
        if (source == null || source.length == 0 || operation == null) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(source).map(x -> operation.map(x)).toArray(String[]::new);
    }

    /**
     * Convert source array in accordance with provided operation.
     *
     * @param source    source array
     * @param operation operation instance
     * @return converted array
     * @throws IllegalArgumentException if parameters not provided
     */
    @Override
    public String[] flatMap(String[] source, FlatMappingOperation operation) {
        if (source == null || source.length == 0 || operation == null) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(source).flatMap(x -> Arrays.stream(operation.flatMap(x)))
                .distinct().mapToInt(Integer::parseInt).boxed().sorted(Collections.reverseOrder())
                .map(s -> String.valueOf(s))
                .toArray(String[]::new);
    }
}
