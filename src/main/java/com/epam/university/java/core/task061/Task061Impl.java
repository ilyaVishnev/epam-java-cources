package com.epam.university.java.core.task061;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task061Impl implements Task061 {

    private Map<Integer, String> numbers = new LinkedHashMap<>();

    {
        numbers.put(3000, "MMM");
        numbers.put(2000, "MM");
        numbers.put(1000, "M");
        numbers.put(900, "CM");
        numbers.put(800, "DCCC");
        numbers.put(700, "DCC");
        numbers.put(600, "DC");
        numbers.put(500, "D");
        numbers.put(400, "CD");
        numbers.put(300, "CCC");
        numbers.put(200, "CC");
        numbers.put(100, "C");
        numbers.put(90, "XC");
        numbers.put(80, "LXXX");
        numbers.put(70, "LXX");
        numbers.put(60, "LX");
        numbers.put(50, "L");
        numbers.put(40, "XL");
        numbers.put(30, "XXX");
        numbers.put(20, "XX");
        numbers.put(10, "X");
        numbers.put(9, "IX");
        numbers.put(8, "VIII");
        numbers.put(7, "VII");
        numbers.put(6, "VI");
        numbers.put(5, "V");
        numbers.put(4, "IV");
        numbers.put(3, "III");
        numbers.put(2, "II");
        numbers.put(1, "I");
    }

    @Override
    public String convertToRoman(int number) {
        if (number >= 4000) {
            throw new IllegalArgumentException();
        }
        String roman = "";
        int count = 0;
        for (Map.Entry<Integer, String> entry : numbers.entrySet()) {
            count = number / entry.getKey();
            if (count != 0) {
                roman += entry.getValue();
                number = number % entry.getKey();
            }
        }
        return roman;
    }

    @Override
    public int convertToArabic(String number) {
        if (number == null || !number.matches("M*D*C*X*L*V*I*")) {
            throw new IllegalArgumentException();
        }
        String[] numbersArray = number.split("");
        int num = 0;
        for (Map.Entry<Integer, String> entry : numbers.entrySet()) {
            String[] newNumbers = containsNumber(numbersArray, entry.getValue());
            if (newNumbers.length != numbersArray.length) {
                num += entry.getKey();
                numbersArray = newNumbers;
            }
        }
        return num;
    }

    private String[] containsNumber(String[] number, String part) {
        String[] partArray = part.split("");
        boolean contains = false;
        int index = 0;
        int min = number.length < partArray.length ? number.length : partArray.length;
        while (index < min) {
            if (partArray[index].equals(number[index])) {
                index++;
                contains = true;
            } else {
                contains = false;
                break;
            }
        }
        if (contains && index == partArray.length) {
            String[] newNumbers = new String[number.length - index];
            System.arraycopy(number, index, newNumbers, 0, newNumbers.length);
            return newNumbers;
        }
        return number;
    }
}
