package com.epam.university.java.core.task029;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Collection;

public class Task029Impl implements Task029 {

    /**
     * Given 10x10 crossword grid, each line in <code>rows</code> collection presents single
     * line in a crossword. Cells in the grid have values + and -. Cells marked with a -
     * need to be filled up with an appropriate characters.
     *
     * <p>
     * Example:
     * source matrix is:
     * +-++++++++
     * +-++++++++
     * +-++++++++
     * +-----++++
     * +-+++-++++
     * +-+++-++++
     * +++++-++++
     * ++------++
     * +++++-++++
     * +++++-++++
     * words list is [LONDON, DELHI, ICELAND, ANKARA]
     * result matrix is
     * +L++++++++
     * +O++++++++
     * +N++++++++
     * +DELHI++++
     * +O+++C++++
     * +N+++E++++
     * +++++L++++
     * ++ANKARA++
     * +++++N++++
     * +++++D++++
     *
     * </p>
     *
     * @param rows  crossword definition
     * @param words words to fill in
     * @return filled crossword
     */
    @Override
    public Collection<String> fillCrossword(Collection<String> rows, Collection<String> words) {
        if (rows == null || words == null) {
            throw new IllegalArgumentException();
        }
        Queue<String> queue = new ArrayDeque<>(words);
        List<String> rowsArray = new ArrayList<>(rows);
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            String word = queue.poll();
            res = putTheWord(new ArrayList<>(rowsArray), new ArrayDeque<>(queue), word);
            if (!res.isEmpty()) {
                break;
            }
            queue.add(word);
        }
        return res;
    }

    private List<String> putTheWord(List<String> rows, Queue<String> words, String word) {
        String[] wordArray = word.split("");
        int indexW = -1;
        boolean row = true;
        int straight = -1;
        boolean done = false;
        for (int i = 0; i < rows.size(); i++) {
            String[] line = rows.get(i).split("");
            for (int j = 0; j < line.length; j++) {
                if (indexW == -1 && line[j].equals("-")) {
                    if ((i != 0 && rows.get(i - 1).split("")[j].matches("[^\\-,\\+]")
                            && !rows.get(i - 1).split("")[j].equals(wordArray[0]))
                            || (j != 0 && line[j - 1].matches("[^\\-,\\+]")
                            && !line[j - 1].equals(wordArray[0]))) {
                        return new ArrayList<>();
                    }
                    if (i != 0 && rows.get(i - 1).split("")[j].equals(wordArray[0])) {
                        straight = j;
                        row = false;
                        indexW = 1;
                        line[j] = wordArray[indexW++];
                    } else if (j != 0 && line[j - 1].equals(wordArray[0])) {
                        straight = i;
                        row = true;
                        indexW = 1;
                        line[j] = wordArray[indexW++];
                    } else {
                        line[j] = wordArray[++indexW];
                    }
                    rows.set(i, String.join("", line));
                    continue;
                }
                if (!done && indexW == 0) {
                    if (line[j].equals("+")) {
                        row = false;
                        straight = j - 1;
                    } else {
                        straight = i;
                        done = true;
                    }
                    indexW++;
                    done = true;
                }
                if (row && i == straight || !row && j == straight) {
                    if (indexW < wordArray.length && (line[j].equals("-")
                            || line[j].equals(wordArray[indexW]))) {
                        line[j] = wordArray[indexW++];
                        rows.set(i, String.join("", line));
                    } else if (indexW == wordArray.length
                            && line[j].equals("+")) {
                        while (!words.isEmpty()) {
                            String next = words.poll();
                            List<String> res = putTheWord(new ArrayList<>(rows),
                                    new ArrayDeque<>(words), next);
                            if (!res.isEmpty()) {
                                return res;
                            }
                            words.add(next);
                        }
                    } else {
                        return new ArrayList<>();
                    }
                }
                if (indexW == wordArray.length
                        && ((!row && i == rows.size() - 1) || (row && j == line.length - 1))) {
                    while (!words.isEmpty()) {
                        String next = words.poll();
                        List<String> res = putTheWord(new ArrayList<>(rows),
                                new ArrayDeque<>(words), next);
                        if (!res.isEmpty()) {
                            return res;
                        }
                        words.add(next);
                    }
                }
            }
        }
        return checkifRowsHasNoMinus(rows);
    }

    private List<String> checkifRowsHasNoMinus(List<String> rows) {
        for (String s : rows) {
            if (s.contains("-")) {
                return new ArrayList<>();
            }
        }
        return rows;
    }
}
