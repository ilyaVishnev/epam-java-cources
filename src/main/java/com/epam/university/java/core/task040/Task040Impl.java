package com.epam.university.java.core.task040;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class Task040Impl implements Task040 {

    private int multyplyX = 0;
    private int multyplyLine = 0;
    private int count = 0;
    private int prev = 0;
    private Deque<Integer> substrucktX = new ArrayDeque<>();

    @Override
    public int countScore(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException();
        }
        String[] frames = str.split(" ");
        for (int i = 0; i < frames.length; i++) {
            String[] separateFrame = frames[i].split("");
            prev = 0;
            for (int j = 0; j < separateFrame.length; j++) {
                if (i == 9 && j > 0) {
                    multyplyX = multyplyX == 0 ? 0 : multyplyX - 1;
                    multyplyLine = 0;
                    if (multyplyX != 0) {
                        substrucktX.offerLast(1);
                    } else {
                        substrucktX.clear();
                    }
                }
                prev = separateFrame[j].matches("\\d")
                        ? Integer.parseInt(separateFrame[j]) :
                        separateFrame[j].equals("X") ? 10 : 10 - prev;
                count += prev + prev * multyplyX + prev * multyplyLine;
                multyplyLine = 0;
                multyplyLine += separateFrame[j].equals("/") ? 1 : 0;
                if (!substrucktX.isEmpty()) {
                    substrucktX = new ArrayDeque<>(substrucktX.stream()
                            .map(e -> e - 1).collect(Collectors.toList()));
                    int index = substrucktX.peek();
                    if (index == 0) {
                        multyplyX--;
                        substrucktX.poll();
                    }
                }
                if (separateFrame[j].equals("X")) {
                    multyplyX++;
                    substrucktX.offerLast(2);
                }
            }
        }
        return count;
    }
}
