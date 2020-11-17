package com.epam.university.java.core.task057;

public class WindowImpl implements Window {

    private int levelNumber;
    private int numberOfWindow;
    private SideType side;

    public WindowImpl() {
    }

    @Override
    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public int getNumberOfWindow() {
        return numberOfWindow;
    }

    @Override
    public SideType getSide() {
        return side;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setNumberOfWindow(int numberOfWindow) {
        this.numberOfWindow = numberOfWindow;
    }

    public void setSide(SideType side) {
        this.side = side;
    }
}
