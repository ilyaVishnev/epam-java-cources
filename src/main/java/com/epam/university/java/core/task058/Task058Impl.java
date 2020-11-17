package com.epam.university.java.core.task058;

public class Task058Impl implements Task058 {


    @Override
    public int[][] fillSpiral(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        int[][] a = new int[n][n];
        int row = 0;
        int column = 0;
        Direction direction = Direction.RIGHT;
        for (int i = 1; i <= Math.pow(n, 2); i++) {
            a[row][column] = i;
            switch (direction) {
                case UP:
                    if (a[row - 1][column] != 0) {
                        direction = Direction.RIGHT;
                        column++;
                    } else {
                        row--;
                    }
                    break;
                case DOWN:
                    if (row == n - 1 || a[row + 1][column] != 0) {
                        direction = Direction.LEFT;
                        column--;
                    } else {
                        row++;
                    }
                    break;
                case LEFT:
                    if (column == 0
                            || a[row][column - 1] != 0) {
                        direction = Direction.UP;
                        row--;
                    } else {
                        column--;
                    }
                    break;
                case RIGHT:
                    if (column == n - 1
                            || a[row][column + 1] != 0) {
                        direction = Direction.DOWN;
                        row++;
                    } else {
                        column++;
                    }
                    break;
                default:
                    break;
            }
        }
        return a;
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
