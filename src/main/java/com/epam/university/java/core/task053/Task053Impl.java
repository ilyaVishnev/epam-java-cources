package com.epam.university.java.core.task053;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Task053Impl implements Task053 {

    private Deque<String> polishNotation = new ArrayDeque<>();
    private Deque<String> stack = new ArrayDeque<>();
    private Map<String, Integer> precedence = Map.of(
            "^", 4,
            "*", 3,
            "/", 3,
            "+", 2,
            "-", 2,
            "(", 5
    );


    @Override
    public double calculate(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        boolean startNumber = false;
        String[] inputArr = input.split("");
        double result = 0;
        try {
            for (String n : inputArr) {
                switch (n) {
                    case "+":
                    case "-":
                    case "^":
                    case "*":
                    case "/":
                    case "(":
                        while (true) {
                            String lastStackElement = stack.isEmpty()
                                    ? "" : stack.getFirst();
                            if (!lastStackElement.equals("(")
                                    && !lastStackElement.equals("")
                                    && precedence.get(lastStackElement) >= precedence
                                    .get(n)) {
                                polishNotation.addLast(stack.pollFirst());
                            } else {
                                stack.addFirst(n);
                                break;
                            }
                            lastStackElement = stack.isEmpty()
                                    ? "" : stack.getFirst();
                        }
                        startNumber = false;
                        break;
                    case ")":
                        String lastStackElement = stack.pollFirst();
                        while (!lastStackElement.equals("(")) {
                            polishNotation.addLast(lastStackElement);
                            lastStackElement = stack.pollFirst();
                        }
                        startNumber = false;
                        break;
                    default:
                        if (n.matches("[0-9]")) {
                            if (startNumber) {
                                String firstPart = polishNotation.pollLast();
                                polishNotation.addLast(firstPart + n);
                            } else {
                                polishNotation.addLast(n);
                            }
                            startNumber = true;
                        } else {
                            throw new IllegalArgumentException();
                        }
                }
            }
            while (!stack.isEmpty()) {
                polishNotation.addLast(stack.pollFirst());
            }
            result = readPolishNotation();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private double readPolishNotation() {
        Deque<Double> numbers = new ArrayDeque<>();
        double first = 0;
        double second = 0;
        for (String s : polishNotation) {
            switch (s) {
                case "+":
                    try {
                        second = numbers.pollLast();
                        first = numbers.pollLast();
                    } catch (Exception ex) {
                        throw new IllegalArgumentException();
                    }
                    numbers.addLast(first + second);
                    break;
                case "-":
                    try {
                        second = numbers.pollLast();
                        first = numbers.pollLast();
                    } catch (Exception ex) {
                        throw new IllegalArgumentException();
                    }
                    numbers.addLast(first - second);
                    break;
                case "*":
                    try {
                        second = numbers.pollLast();
                        first = numbers.pollLast();
                    } catch (Exception ex) {
                        throw new IllegalArgumentException();
                    }
                    numbers.addLast(first * second);
                    break;
                case "/":
                    try {
                        second = numbers.pollLast();
                        first = numbers.pollLast();
                    } catch (Exception ex) {
                        throw new IllegalArgumentException();
                    }
                    numbers.addLast(first / second);
                    break;
                case "^":
                    try {
                        second = numbers.pollLast();
                        first = numbers.pollLast();
                    } catch (Exception ex) {
                        throw new IllegalArgumentException();
                    }
                    numbers.addLast(Math.pow(first, second));
                    break;
                default:
                    if (s.matches("\\d+")) {
                        numbers.addLast(Double.parseDouble(s));
                    } else {
                        throw new IllegalArgumentException();
                    }
            }
        }
        return numbers.getFirst();
    }
}
