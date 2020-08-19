package com.epam.university.java.core.task001;

public class Task001Impl implements Task001 {


    @Override
    public double addition(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        firstNumber = firstNumber.replaceAll(" ", "");
        secondNumber = secondNumber.replaceAll(" ", "");
        if (firstNumber == "" || secondNumber == "") {
            throw new IllegalArgumentException();
        }
        Double first = Double.parseDouble(firstNumber);
        Double second = Double.parseDouble(secondNumber);
        return first + second;
    }

    @Override
    public double subtraction(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        firstNumber = firstNumber.replaceAll(" ", "");
        secondNumber = secondNumber.replaceAll(" ", "");
        if (firstNumber == "" || secondNumber == "") {
            throw new IllegalArgumentException();
        }
        Double first = Double.parseDouble(firstNumber);
        Double second = Double.parseDouble(secondNumber);
        return first - second;
    }

    @Override
    public double multiplication(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        firstNumber = firstNumber.replaceAll(" ", "");
        secondNumber = secondNumber.replaceAll(" ", "");
        if (firstNumber == "" || secondNumber == "") {
            throw new IllegalArgumentException();
        }
        Double first = Double.parseDouble(firstNumber);
        Double second = Double.parseDouble(secondNumber);
        return first * second;
    }

    @Override
    public double division(String firstNumber, String secondNumber) {
        if (firstNumber == null || secondNumber == null) {
            throw new IllegalArgumentException();
        }
        firstNumber = firstNumber.replaceAll(" ", "");
        secondNumber = secondNumber.replaceAll(" ", "");
        if (firstNumber == "" || secondNumber == "") {
            throw new IllegalArgumentException();
        }
        Double first = Double.parseDouble(firstNumber);
        Double second = Double.parseDouble(secondNumber);
        return first / second;
    }
}