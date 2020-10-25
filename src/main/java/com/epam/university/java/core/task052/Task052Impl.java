package com.epam.university.java.core.task052;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task052Impl implements Task052 {


    @Override
    public boolean validateCard(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        List<Integer> numbers = new ArrayList<>(Stream.of(String.valueOf(number).split(""))
                .map(i -> Integer.parseInt(i))
                .collect(Collectors.toList()
                ));
        Collections.reverse(numbers);
        if (numbers.size() < 14
                && numbers.size() > 19) {
            return false;
        }
        int last = numbers.get(0);
        numbers.remove(0);
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                numbers.set(i, isHaveOne(numbers.get(i) * 2));
            }
        }
        String sum = numbers.stream().mapToInt(i -> i.intValue()).sum() + "";
        if (10 - Character.getNumericValue(sum.charAt(sum.length() - 1))
                == last) {
            return true;
        }

        return false;
    }

    private int isHaveOne(int multiply) {
        String source = multiply + "";
        if (source.matches(".*[1]+.*")) {
            return Character.getNumericValue(source.charAt(0))
                    + Character.getNumericValue(source.charAt(1));
        }
        return multiply;
    }
}
