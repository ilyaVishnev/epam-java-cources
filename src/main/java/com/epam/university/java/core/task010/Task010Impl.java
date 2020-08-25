package com.epam.university.java.core.task010;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Task010Impl implements Task010 {

    /**
     * Given a textual file, you should count frequency of words in this file.
     *
     * @param source source file
     * @return map word to frequency of it
     */
    @Override
    public Map<String, Integer> countWordNumbers(File source) {
        List<String> text = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));) {
            String s;
            while ((s = reader.readLine()) != null) {
                text.addAll(Arrays.asList(s.replaceAll("\\p{Punct}", "").toLowerCase().split(" ")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Map<String, Integer> map = new HashMap<>();
        for (String t : text) {
            map.put(t, Collections.frequency(text, t));
        }
        return map;
    }
}
