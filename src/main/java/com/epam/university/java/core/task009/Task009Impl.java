package com.epam.university.java.core.task009;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Task009Impl implements Task009 {

    /**
     * Source file contains words which separated with spaces.
     * <p>
     * You need to output all different words.
     * Same word in upper and lower case should be counted as equal.
     * </p>
     * <p>
     * Tip: you can use Set for it.
     * </p>
     *
     * @param sourceFile source file
     * @return collection of different words
     */
    @Override
    public Collection<String> countWords(File sourceFile) {
        Set<String> words = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));) {
            String s;
            while ((s = reader.readLine()) != null) {

                words.addAll(Arrays.asList(s.toLowerCase().replaceAll("\\p{Punct}",
                        "").split(" ")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return words;
    }
}
