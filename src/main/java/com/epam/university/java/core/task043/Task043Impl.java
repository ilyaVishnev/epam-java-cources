package com.epam.university.java.core.task043;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Task043Impl implements Task043 {

    private Map<String, List<String>> graph = new HashMap<>();
    private Map<String, String> result = new HashMap<>();

    @Override
    public String encode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        fillGraph();
        coding("start", "");
        String code = "";
        List<String> source = Arrays.asList(sourceString.split(""));
        for (int i = 0; i < source.size(); i++) {
            if (result.get(source.get(i)) != null) {
                code += result.get(source.get(i));
            }
            if (" ".equals(source.get(i))) {
                code += "  ";
            } else if (i != source.size() - 1) {
                code += " ";
            }
        }
        return code;
    }

    private void coding(String key, String code) {
        List<String> list = new ArrayList<>();
        if (graph.get(key) != null) {
            list = graph.get(key);
            if (list.size() == 1) {
                coding(list.get(0), code + ".");
                result.put(list.get(0), code + ".");
            } else {
                coding(list.get(0), code + ".");
                coding(list.get(1), code + "-");
                result.put(list.get(0), code + ".");
                result.put(list.get(1), code + "-");
            }
        }
        result.put(key, code);
    }

    private void fillGraph() {
        graph.put("start", Arrays.asList("E", "T"));
        graph.put("E", Arrays.asList("I", "A"));
        graph.put("T", Arrays.asList("N", "M"));
        graph.put("I", Arrays.asList("S", "U"));
        graph.put("A", Arrays.asList("R", "W"));
        graph.put("N", Arrays.asList("D", "K"));
        graph.put("M", Arrays.asList("G", "O"));
        graph.put("S", Arrays.asList("H", "V"));
        graph.put("U", Arrays.asList("F","U:"));
        graph.put("R", Arrays.asList("L"));
        graph.put("W", Arrays.asList("P", "J"));
        graph.put("D", Arrays.asList("B", "X"));
        graph.put("K", Arrays.asList("C", "Y"));
        graph.put("G", Arrays.asList("Z", "Q"));
        graph.put("Z", Arrays.asList("7", "COMMA"));
        graph.put("COMMA", Arrays.asList("EMPTY", ","));
        graph.put("H", Arrays.asList("5", "4"));
        graph.put("V", Arrays.asList("S^", "3"));
        graph.put("U:", Arrays.asList("D-", "2"));
        graph.put("J", Arrays.asList("J-", "1"));
        graph.put("B", Arrays.asList("6"));
        graph.put("O", Arrays.asList("O:","CH"));
        graph.put("O:", Arrays.asList("8"));
        graph.put("CH", Arrays.asList("9", "0"));
    }

    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String> encode = result.entrySet().stream().collect(Collectors
                .toMap(x -> x.getValue(), y -> y.getKey()));
        List<String> source = Arrays.asList(sourceString.split(""));
        String decodeStr = "";
        String newStr = "";
        int countSpace = 0;
        for (int i = 0; i < source.size(); i++) {
            if (source.get(i).equals(" ")) {
                countSpace++;
                if (countSpace == 1 && encode.get(newStr) != null) {
                    decodeStr += encode.get(newStr);
                    newStr = "";
                }
            } else {
                countSpace = 0;
                newStr += source.get(i);
            }
            if (countSpace == 3) {
                decodeStr += " ";
                countSpace = 0;
            }
            if (i == source.size() - 1) {
                decodeStr += encode.get(newStr);
            }

        }
        return decodeStr;
    }
}
