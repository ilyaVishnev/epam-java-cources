package com.epam.university.java.core.task014;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task014Impl implements Task014 {

    List<VampireNumber> vampireNumbers = new ArrayList<>();

    /**
     * A vampire number has an even number of digits and is formed by
     * multiplying a pair of numbers containing half the number of digits
     * of the result. The digits are taken from the original number
     * in any order. Pairs of trailing zeroes are not allowed.
     *
     * <p>
     * Example: 1260 = 21 * 60
     * </p>
     * <p>
     * {@see https://en.wikipedia.org/wiki/Vampire_number}
     * </p>
     *
     * @return collection of vampire numbers
     */
    public Collection<VampireNumber> getVampireNumbers() {
        for (int i = 1000; i < Math.pow(10, 4); i++) {
            String[] number = String.valueOf(i).split("");
            VampireNumber vampireNumber = checkIfVampire(getAllCombinations(number), i);
            if (vampireNumber != null) {
                vampireNumbers.add(vampireNumber);
            }
        }
        return vampireNumbers;
    }

    private List<Integer> getAllCombinations(String[] number) {
        List<Integer> combinations = new ArrayList<>();
        Integer size = IntStream.rangeClosed(number.length / 2 + 1, number.length)
                .mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get().intValueExact();
        int pointer = 1;
        int index = 0;
        List<Integer> nextNumber = new ArrayList<>(Collections.nCopies(number.length / 2, 0));
        while (combinations.size() < size) {
            int value = nextNumber.get(pointer) + 1;
            if (nextNumber.contains(value)) {
                nextNumber.set(pointer, value);
                continue;
            }
            if (value >= number.length) {
                nextNumber.set(pointer, -1);
                pointer--;
                continue;
            } else {
                nextNumber.set(pointer, value);
            }
            if (pointer == number.length / 2 - 1) {
                if (!number[nextNumber.get(0)].equals("0")) {
                    String s = "";
                    for (Integer i : nextNumber) {
                        s += number[i];
                    }
                    combinations.add(Integer.parseInt(s));
                } else {
                    size--;
                }
            }
            pointer = pointer == nextNumber.size() - 1 ? pointer : pointer + 1;
        }
        return combinations;
    }

    private VampireNumber checkIfVampire(List<Integer> combinations, Integer number) {
        VampireNumberImpl vampireNumber = null;
        boolean exit = false;
        for (Integer i : combinations) {
            for (Integer j : combinations) {
                if ((i % 10 != 0 || j % 10 != 0) && i * j == number
                        && checkTwoNumbers(number, i, j)) {
                    vampireNumber = new VampireNumberImpl();
                    vampireNumber.setFirst(i);
                    vampireNumber.setSecond(j);
                    vampireNumber.setMultiplication(number);
                    exit = true;
                    break;
                }
            }
            if (exit) {
                break;
            }
        }
        return vampireNumber;
    }

    private boolean checkTwoNumbers(Integer number, Integer first, Integer sec) {
        List<String> common = new ArrayList<>(Arrays.asList(String.valueOf(first).split("")));
        common.addAll(new ArrayList<>(Arrays.asList(String.valueOf(sec).split(""))));
        List<String> numberL = Arrays.asList(String.valueOf(number).split(""));
        for (String el : numberL) {
            common.remove(el);
        }
        return common.size() == 0 ? true : false;
    }
}
