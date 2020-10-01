package com.epam.university.java.core.task027;

import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task027Impl implements Task027 {

    /**
     * Given a number string that can be split into the sequence of two or more positive
     * integers [a1, a2, ..., an] satisfying the following conditions:
     *
     * <p>
     * 1. ai - a(i-1) = 1 for any element
     * 2. No ai contains a leading zero
     * 3. Content of the sequence cannot be rearranged.
     * </p>
     *
     * <p>
     * You should extract that numbers or return an empty collection if it is not possible.
     * </p>
     *
     * <p>
     * Example: given a string "1234", result should be [1, 2, 3, 4]
     * Example: given a string "91011", result should be [9, 10, 11]
     * Example: given a string "99100", result should be [99, 100]
     * Example: given a string "4123", result should be []
     * </p>
     *
     * @param sourceString source string
     * @return collection of extracted integers
     */
    @Override
    public Collection<Integer> extract(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        if (sourceString.equals("") || sourceString.split("")[0].equals("0")) {
            return new ArrayList<>();
        }
        Set<Integer> res = new TreeSet<>();
        String[] sourceArray = sourceString.split("");
        List<String> next = new ArrayList<>();
        List<String> prev = new ArrayList<>();
        int nextIndex = 0;
        boolean isnext = false;
        for (String s : sourceArray) {
            if (!next.isEmpty() && next.get(nextIndex).equals(s)) {
                isnext = true;
                nextIndex++;
            } else {
                if (!res.isEmpty()) {
                    String newRes = res.stream().map(Objects::toString)
                            .collect(Collectors.joining());
                    res.clear();
                    res.add(Integer.parseInt(newRes));
                    next = new ArrayList<>(Arrays.asList((""
                            + (Integer.parseInt(newRes) + 1))
                            .split("")));
                    nextIndex = 0;
                    prev = new ArrayList<>(Arrays.asList(newRes));
                    if (!next.isEmpty() && next.get(nextIndex).equals(s)) {
                        isnext = true;
                        nextIndex++;
                    }
                } else {
                    prev.add(s);
                    next.clear();
                    nextIndex = 0;
                    isnext = false;
                }
            }
            if (!prev.isEmpty() && next.isEmpty()) {
                next.addAll(Arrays.asList((""
                        + (Integer.parseInt(String.join("", prev))
                        + 1)).split("")));
            }
            if (isnext && nextIndex == next.size()) {
                res.add(Integer.parseInt(String.join("", prev)));
                res.add(Integer.parseInt(String.join("", next)));
                prev = next;
                next = new ArrayList<>(Arrays.asList((""
                        + (Integer.parseInt(String.join("", next)) + 1))
                        .split("")));
                isnext = false;
                nextIndex = 0;
            }
        }
        if (!res.isEmpty()
                && res.stream().map(Objects::toString)
                .collect(Collectors.joining()).length() == sourceString.length()) {
            return res;
        }
        return new ArrayList<>();
    }
}
