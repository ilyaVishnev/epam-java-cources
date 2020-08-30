package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {

    int multiplication;
    int first;
    int second;

    /**
     * Get multiplication of numbers.
     *
     * @return value of multiplication
     */
    public int getMultiplication() {
        return this.multiplication;
    }

    /**
     * Get first part of vampire number.
     *
     * @return value if first part
     */
    public int getFirst() {
        return this.first;
    }

    /**
     * Get second part of vampire number.
     *
     * @return value of second part
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * Check if two vampire numbers are equals in spite of the order
     * of parts.
     *
     * @param value vampire number to check
     * @return if numbers are equals
     */
    public boolean equals(Object value) {
        if (value == null) {
            return false;
        }
        if (this.getClass() != value.getClass()) {
            return false;
        }
        return true;
    }

    public void setMultiplication(int multiplication) {
        this.multiplication = multiplication;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
