package com.epam.university.java.core.task057;

public class Task057Impl implements Task057 {


    @Override
    public Window getWindowForDelivery(int level, int entrances, int numberOfFlat) {
        if (numberOfFlat > level * 8 * entrances
                || numberOfFlat <= 0) {
            throw new IllegalArgumentException();
        }
        WindowImpl window = new WindowImpl();
        int numOfFlatOnLevel = numberOfFlat % 8;
        int levelFlat = numberOfFlat % (level * 8) == 0
                ? level : (numberOfFlat % (level * 8)) / 8 + 1;
        int countEntrancesFromLeft = numberOfFlat % (level * 8) == 0
                ? numberOfFlat / (level * 8) - 1
                : numberOfFlat / (level * 8);
        window.setLevelNumber(levelFlat);
        SideType sideType = null;
        switch (numOfFlatOnLevel) {
            case 0:
                window.setSide(SideType.FRONT_SIDE);
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 4);
                break;
            case 1:
                window.setSide(SideType.FRONT_SIDE);
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 6);
                break;
            case 2:
                window.setSide(SideType.FRONT_SIDE);
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 8);
                break;
            case 3:
                window.setSide(SideType.BACK_SIDE);
                countEntrancesFromLeft = entrances - countEntrancesFromLeft - 1;
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 2);
                break;
            case 4:
                window.setSide(SideType.BACK_SIDE);
                countEntrancesFromLeft = entrances - countEntrancesFromLeft - 1;
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 4);
                break;
            case 5:
                window.setSide(SideType.BACK_SIDE);
                countEntrancesFromLeft = entrances - countEntrancesFromLeft - 1;
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 6);
                break;
            case 6:
                window.setSide(SideType.BACK_SIDE);
                countEntrancesFromLeft = entrances - countEntrancesFromLeft - 1;
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 8);
                break;
            case 7:
                window.setSide(SideType.FRONT_SIDE);
                window.setNumberOfWindow(countEntrancesFromLeft * 8 + 2);
                break;
            default:
                break;
        }
        return window;
    }
}
