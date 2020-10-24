package com.epam.university.java.core.task048;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Task048Impl implements Task048 {

    private Set<Integer> numbers = new TreeSet<>();

    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from,
                                                   Integer to) {
        if (from == null || to == null || from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = from; i <= to; i++) {
            if (checkIfArmstrong(i)) {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private boolean checkIfArmstrong(int number) {
        String[] numbStr = String.valueOf(number).split("");
        if (numbStr.length == 1) {
            return true;
        }
        int prev = 0;
        int sum = 0;
        for (String n : numbStr) {
            int actual = Integer.parseInt(n);
            prev = actual;
            sum += Math.pow(actual, numbStr.length);
        }
        if (sum == number) {
            return true;
        }
        return false;
    }
}
