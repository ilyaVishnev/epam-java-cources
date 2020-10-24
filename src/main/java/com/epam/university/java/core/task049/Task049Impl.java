package com.epam.university.java.core.task049;

public class Task049Impl implements Task049 {


    @Override
    public String getResultList(String first, String second) {
        if (first == null || second == null || first.matches("\\s+")
                || second.matches("\\s+")) {
            throw new IllegalArgumentException();
        }
        String max = "";
        String actual = "";
        for (int i = 0; i < first.length(); i++) {
            int k = i;
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(k) == second.charAt(j)) {
                    actual += first.charAt(k);
                    if (actual.length() > max.length()) {
                        max = actual;
                    }
                    if (k == first.length() - 1) {
                        actual = "";
                        break;
                    }
                    k++;
                } else {
                    actual = "";
                    k = i;
                }
            }
        }
        return max;
    }
}
