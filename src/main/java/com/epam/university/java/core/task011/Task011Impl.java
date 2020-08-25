package com.epam.university.java.core.task011;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task011Impl implements Task011 {

    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with arrays
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(String[] collection) {
        if (collection.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = -1;
        int countAlive = collection.length;
        boolean next = true;
        while (true) {
            index = index == collection.length - 1 ? 0 : index + 1;
            if (collection[index] == null) {
                continue;
            }
            if (countAlive == 1) {
                break;
            }
            if (next) {
                collection[index] = null;
                next = false;
                countAlive--;
            } else {
                next = true;
            }
        }
        return collection[index];
    }

    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with ArrayList
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(ArrayList<String> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        int index = -1;
        int countAlive = collection.size();
        boolean next = true;
        while (true) {
            index = index == collection.size() - 1 ? 0 : index + 1;
            if (collection.get(index) == null) {
                continue;
            }
            if (countAlive == 1) {
                break;
            }
            if (next) {
                collection.set(index, null);
                next = false;
                countAlive--;
            } else {
                next = true;
            }
        }
        return collection.get(index);
    }

    /**
     * Given a circle of men, on each iteration one man leaves it through one. You should determine
     * name of last man.
     *
     * <p>
     * Example: source collection: Homer Bart Maggie Lisa Marge
     * First iteration: Homer leaves, Bart Maggie List Marge remained
     * Second iteration: Maggie leaves, Bart List Marge remained
     * Third iteration: Marge leaves, Bart and Lise remained
     * Fourth iteration: Lisa leaves, Bart is the last one
     * </p>
     * <p>
     * Implementation with LinkedList
     * </p>
     *
     * @param collection collection of names
     * @return name of last man
     */
    @Override
    public String getLastName(LinkedList<String> collection) {
        if (collection.size() == 0) {
            throw new IllegalArgumentException();
        }
        int index = -1;
        int countAlive = collection.size();
        boolean next = true;
        while (true) {
            index = index == collection.size() - 1 ? 0 : index + 1;
            if (collection.get(index) == null) {
                continue;
            }
            if (countAlive == 1) {
                break;
            }
            if (next) {
                collection.set(index, null);
                next = false;
                countAlive--;
            } else {
                next = true;
            }
        }
        return collection.get(index);
    }
}
