package com.epam.university.java.core.task023;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task023Impl implements Task023 {
    @Override
    public String extract(String phoneString) {
        if (!phoneString.matches("(\\+?)\\d(\\s?)(\\(?)(\\s?)\\d{3}"
                + "(\\s?)(\\)?)(\\s?)\\d{3}(\\s?)-?\\d{2}(\\s?)-?\\d{2}")
        ) {
            throw new IllegalArgumentException();
        }
        Pattern pattern = Pattern.compile("(\\+?)\\d(\\s?)(\\(?)(\\s?)\\d{3}");
        Matcher m = pattern.matcher(phoneString);
        m.find();
        String res = phoneString.substring(m.end() - 3, m.end());
        return res;
    }
}
