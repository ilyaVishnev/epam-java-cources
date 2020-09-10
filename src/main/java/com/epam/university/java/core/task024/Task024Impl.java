package com.epam.university.java.core.task024;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task024Impl implements Task024 {

    /**
     * Given a string with camel case word, you should separate this string
     * into collection of words.
     *
     * <p>
     * Example: source string is saveChangesInTheEditor, result is
     * [save, changes, in, the, editor]
     * </p>
     *
     * @param source source string
     * @return collection of words
     */
    @Override
    public Collection<String> getWordsCount(String source) {
        List<String> res = new ArrayList<>();
        String[] sourceArray = source.split("");
        if (source.equals("")) {
            return new ArrayList<>();
        }
        String word = "";
        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i].toUpperCase().equals(sourceArray[i]) && i != 0) {
                res.add(word.toLowerCase());
                word = "";
            }
            word += sourceArray[i];
        }
        res.add(word.toLowerCase());
        return res;
    }
}
