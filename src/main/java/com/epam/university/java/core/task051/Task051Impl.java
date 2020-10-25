package com.epam.university.java.core.task051;

import java.util.Arrays;

public class Task051Impl implements Task051 {


    @Override
    public String replace(String source) {
        if (source == null || source.matches("\\s+")) {
            throw new IllegalArgumentException();
        }
        String[] sourceStr = source.split(" ");
        int start = -1;
        for (int i = 0; i < sourceStr.length; i++) {
            if (sourceStr[i].equals("the")) {
                start = i;
                continue;
            }
            if (start != -1) {
                if (String.valueOf(sourceStr[i].charAt(0))
                        .matches("[a-z&&[^aeuio]]")) {
                    sourceStr[start] = "a";
                } else {
                    sourceStr[start] = "an";
                }
            }
            start = -1;
        }
        if (String.join(" ", sourceStr).equals(source)) {
            throw new IllegalArgumentException();
        }
        return String.join(" ", sourceStr);
    }
}
