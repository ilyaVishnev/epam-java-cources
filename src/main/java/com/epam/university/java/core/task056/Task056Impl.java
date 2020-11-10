package com.epam.university.java.core.task056;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task056Impl implements Task056 {

    private List<String> unorderedDays = new ArrayList<>();
    private List<Integer> packsNumber = new ArrayList<>();

    @Override
    public Collection<Integer> necessaryMedication(String prescriptionFile) {
        if (prescriptionFile == null) {
            throw new IllegalArgumentException();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(prescriptionFile)));
            String s;
            while ((s = reader.readLine()) != null) {
                unorderedDays.add(s);
            }
            int max = 0;
            List<String> maxList = new ArrayList<>();
            for (int i = 0; i < unorderedDays.size(); i++) {
                List<String> packs = getAllPacks(new ArrayList<>(), i);
                int newMax = packs.stream().mapToInt(el -> Integer.parseInt(el.split(" ")[0]))
                        .sum();
                if (newMax > max) {
                    max = newMax;
                    maxList = packs;
                }
            }
            for (int i = 0; i < unorderedDays.size(); i++) {
                if (maxList.contains(unorderedDays.get(i))) {
                    packsNumber.add(i);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return packsNumber;
    }

    @Override
    public Collection<String> intervalBetweenMedication(Collection<Integer> necessaryMedication) {
        if (necessaryMedication == null) {
            throw new IllegalArgumentException();
        }
        List<String> intervals = new ArrayList<>();
        List<String> orderedDays = IntStream.range(0, unorderedDays.size())
                .filter(i -> packsNumber.contains(i))
                .mapToObj(unorderedDays::get).collect(Collectors.toList());
        Collections.sort(orderedDays, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int firstO1 = Integer.parseInt(o1.split(" ")[1].split("-")[0]);
                int secO1 = Integer.parseInt(o1.split(" ")[1].split("-")[1]);
                int firstO2 = Integer.parseInt(o2.split(" ")[1].split("-")[0]);
                int secO2 = Integer.parseInt(o2.split(" ")[1].split("-")[1]);
                return secO1 - firstO2;
            }
        });
        boolean lastEl = true;
        int first = 0;
        int sec = 0;
        for (String s : orderedDays) {
            if (lastEl) {
                first = Integer.parseInt(s.split(" ")[1].split("-")[1]) + 1;
                lastEl = false;
            } else {
                sec = Integer.parseInt(s.split(" ")[1].split("-")[0]) - 1;
                if (sec >= first) {
                    intervals.add(first + "-" + sec);
                }
                first = Integer.parseInt(s.split(" ")[1].split("-")[1]) + 1;
            }
        }
        return intervals;
    }

    private List<String> getAllPacks(List<String> ordersDay, int lastIndex) {
        int firstlast = Integer.parseInt(unorderedDays.get(lastIndex).split(" ")[1].split("-")[0]);
        int seclast = Integer.parseInt(unorderedDays.get(lastIndex).split(" ")[1].split("-")[1]);
        int max = Integer.parseInt(unorderedDays.get(lastIndex).split(" ")[0]);
        List<String> result = new ArrayList<>();
        ordersDay.add(unorderedDays.get(lastIndex));
        List<String> maxList = new ArrayList<>(ordersDay);
        for (int i = lastIndex + 1; i < unorderedDays.size(); i++) {
            int first = Integer.parseInt(unorderedDays.get(i).split(" ")[1].split("-")[0]);
            int sec = Integer.parseInt(unorderedDays.get(i).split(" ")[1].split("-")[1]);
            if (checkIfInsideInterval(ordersDay, first, sec)) {
                result = getAllPacks(new ArrayList<>(ordersDay), i);
            }
            int newMax = result.stream().mapToInt(s -> Integer.parseInt(s.split(" ")[0])).sum();
            if (newMax > max) {
                max = newMax;
                maxList = result;
            }
        }
        return maxList;
    }

    private boolean checkIfInsideInterval(List<String> list, int start, int end) {
        boolean res = false;
        for (String s : list) {
            int first = Integer.parseInt(s.split(" ")[1].split("-")[0]);
            int sec = Integer.parseInt(s.split(" ")[1].split("-")[1]);
            if (first > end
                    || sec < start) {
                res = true;
            } else {
                return false;
            }
        }
        return res;
    }
}
