package com.epam.university.java.core.task039;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Task039Impl implements Task039 {

    private Map<String, List<Map.Entry<String, Integer>>> graph = new TreeMap<>();
    private Map<Character, String> result = new TreeMap<>();
    private Comparator<Map.Entry<String, Integer>> comparator = new Comparator
            <Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                           Map.Entry<String, Integer> o2) {
            return o1.getValue() - o2.getValue() == 0
                    ? o2.getKey().compareTo(o1.getKey())
                    : o1.getValue() - o2.getValue();
        }
    };
    private Map<String, Integer> strFrequencies = new TreeMap<>();

    @Override
    public Map<Character, String> getEncoding(Map<Character, Integer> charFrequencies) {
        strFrequencies = new TreeMap<>(charFrequencies.entrySet().stream()
                .collect(Collectors.toMap(x -> String.valueOf(x.getKey()), y -> y.getValue())));
        Queue<Map.Entry<String, Integer>> queue = new ArrayDeque<>(strFrequencies.entrySet()
                .stream().sorted(comparator).collect(Collectors.toCollection(ArrayDeque::new)));
        fillGraph(queue, comparator);
        String root = graph.keySet().stream().max(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }).get();
        String binary = "";
        binaryWay(root, binary);
        return result;
    }

    private void binaryWay(String key, String binary) {
        List<Map.Entry<String, Integer>> list = graph.get(key);
        if (list.isEmpty()) {
            result.put(key.charAt(0), binary);
            return;
        }
        Map.Entry<String, Integer> left = list.get(0);
        Map.Entry<String, Integer> rigth = list.get(1);
        String first = "0";
        String sec = "1";
        if (left.getKey().length() == 1
                && rigth.getKey().length() == 1
                && strFrequencies.get(left.getKey())
                == strFrequencies.get(rigth.getKey())) {
            first = "1";
            sec = "0";
        }
        binaryWay(left.getKey(), binary + first);
        binaryWay(rigth.getKey(), binary + sec);
    }

    private void fillGraph(Queue<Map.Entry<String, Integer>> queue,
                           Comparator<Map.Entry<String, Integer>> comparator) {
        while (queue.size() > 1) {
            Map.Entry<String, Integer> prev = queue.poll();
            Map.Entry<String, Integer> next = queue.poll();
            if (graph.get(prev.getKey()) == null) {
                graph.put(prev.getKey(), new ArrayList<>());
            }
            if (graph.get(next.getKey()) == null) {
                graph.put(next.getKey(), new ArrayList<>());
            }
            Map.Entry<String, Integer> res = new Map.Entry<String, Integer>() {
                @Override
                public String getKey() {
                    return prev.getKey() + next.getKey();
                }

                @Override
                public Integer getValue() {
                    return prev.getValue() + next.getValue();
                }

                @Override
                public Integer setValue(Integer value) {
                    return null;
                }
            };
            queue.offer(res);
            queue = queue.stream().sorted(comparator).collect(Collectors
                    .toCollection(ArrayDeque::new));
            graph.put(res.getKey(), Arrays.asList(prev, next));

        }
    }


    @Override
    public String getEncodedString(Map<Character, Integer> charFrequencies, String string) {
        strFrequencies = new TreeMap<>(charFrequencies.entrySet()
                .stream().collect(Collectors
                        .toMap(x -> String.valueOf(x.getKey()), y -> y.getValue())));
        Queue<Map.Entry<String, Integer>> queue = new ArrayDeque<>(strFrequencies.entrySet()
                .stream().sorted(comparator).collect(Collectors.toCollection(ArrayDeque::new)));
        fillGraph(queue, comparator);
        String root = graph.keySet().stream().max(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }).get();
        String binary = "";
        binaryWay(root, binary);
        char[] chars = string.toCharArray();
        String line = "";
        for (char c : chars) {
            line += result.get(c);
        }
        return line;
    }

    @Override
    public String getDecodedString(Map<Character, Integer> charFrequencies, String encodedString) {
        Map<Character, String> code = getEncoding(charFrequencies);
        Map<String, Character> encode = code.entrySet().stream().collect(Collectors
                .toMap(x -> x.getValue(), y -> y.getKey()));
        char[] chars = encodedString.toCharArray();
        String value = "";
        String res = "";
        for (char c : chars) {
            value += c;
            if (encode.get(value) != null) {
                res += encode.get(value);
                value = "";
            }
        }
        return res;
    }
}
