package com.epam.university.java.core.task020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Task020Impl implements Task020 {

    @Override
    public int calculate(Collection<String> stones) {
        List<String> myStones = new ArrayList<>(stones);
        List<Character> chars = new ArrayList<>();
        List<Character> prev = Arrays.asList(myStones.get(0).chars()
                .mapToObj(c -> (char) c).toArray(Character[]::new));
        for (int i = 1; i < myStones.size(); i++) {
            chars = new ArrayList<>(Arrays.asList(myStones.get(i).chars()
                    .mapToObj(c -> (char) c).toArray(Character[]::new)));
            chars.retainAll(prev);
            prev = chars;
        }
        return chars.size();
    }
}
