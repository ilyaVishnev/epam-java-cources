package com.epam.university.java.core.task028;

public class Task028Impl implements Task028 {

    private int count = 0;

    @Override
    public int getWays(int value, int power) {
        search(value, power, value);
        return count;
    }

    private void search(int value, int power, int sum) {
        for (int i = value - 1; i > 0; i--) {
            int mySumm = sum - (int) Math.pow(i, power);
            if (mySumm < 0) {
                continue;
            }
            if (mySumm == 0) {
                count++;
                continue;
            }
            search(i, power, mySumm);
        }
    }
}
