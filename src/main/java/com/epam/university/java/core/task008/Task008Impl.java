package com.epam.university.java.core.task008;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task008Impl implements Task008 {

    /**
     * Given a string with mathematical expression like "(1 + 2) * {[-3] - 4}". You need to check,
     * are all braces correct. Ex:
     * <p>
     * "(1 + 2) * {[-3] - 4}" - correct
     * "(1 + [2) + 3 - 4]" - incorrect
     * In expression can be used the following kinds of braces: {}, (), [].
     * Tip: it's better to implement stack structure.
     * Tip: You also can use Stack class but it's not recommended.
     * </p>
     *
     * @param sourceString string to check
     * @return is braces valid or source string is empty
     */
    @Override
    public boolean isValid(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        char[] chars = sourceString.toCharArray();
        List<Character> braces = new ArrayList<>(Arrays.asList(' '));
        for (char c : chars) {
            switch (c) {
                case '{':
                    braces.add(c);
                    break;
                case '(':
                    braces.add(c);
                    break;
                case '[':
                    braces.add(c);
                    break;
                case '}':
                    if (braces.remove(braces.size() - 1) != '{') {
                        return false;
                    }
                    break;
                case ')':
                    if (braces.remove(braces.size() - 1) != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (braces.remove(braces.size() - 1) != '[') {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return braces.size() == 1;
    }
}
