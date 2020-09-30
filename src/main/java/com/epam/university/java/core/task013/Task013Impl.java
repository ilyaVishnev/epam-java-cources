package com.epam.university.java.core.task013;


import java.util.Collection;
import java.util.Queue;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;

public class Task013Impl implements Task013 {
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        if (figure == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (FigureAction figureAction : actions) {
            figureAction.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException();
        }
        boolean convex = true;
        Queue<Vertex> expiriencePoints = getPairs(figure);
        while (!expiriencePoints.isEmpty()) {
            Vertex pointOne = expiriencePoints.poll();
            Vertex pointTwo = expiriencePoints.poll();
            double bOne = (double) (pointTwo.getY() * pointOne.getX()
                    - pointOne.getY() * pointTwo.getX())
                    / (pointOne.getX() - pointTwo.getX());
            double kOne = (double) (pointOne.getY() - pointTwo.getY())
                    / (pointOne.getX() - pointTwo.getX());
            if (crossing(bOne, kOne, figure, pointOne, pointTwo)) {
                return false;
            }
        }
        return convex;
    }

    private boolean crossing(double bOne, double kOne, Figure figure,
                             Vertex pointOne, Vertex pointTwo) {
        Queue<Vertex> checkPoints = getPairs(figure);
        checkPoints.remove(pointOne);
        checkPoints.remove(pointTwo);
        while (!checkPoints.isEmpty()) {
            Vertex pointExpOne = checkPoints.poll();
            Vertex pointExpTwo = checkPoints.poll();
            double bTwo = (double) (pointExpTwo.getY() * pointExpOne.getX()
                    - pointExpOne.getY() * pointExpTwo.getX())
                    / (pointExpOne.getX() - pointExpTwo.getX());
            double kTwo = (double) (pointExpOne.getY() - pointExpTwo.getY())
                    / (pointExpOne.getX() - pointExpTwo.getX());
            int xCros;
            int yCros;
            if (Math.abs(kOne) == 0 && Math.abs(kTwo) == 0
                    || (pointOne.getX() == pointTwo.getX()
                    && pointExpOne.getX() == pointExpTwo.getX())
                    || (pointOne.getY() == pointTwo.getY()
                    && pointExpOne.getY() == pointExpTwo.getY())) {
                continue;
            }
            if (Double.isNaN(bOne) || Double.isInfinite(bOne)) {
                xCros = pointOne.getX();
                yCros = (int) (xCros * kTwo + bTwo);
            } else if (Double.isNaN(bTwo) || Double.isInfinite(bOne)) {
                xCros = pointExpOne.getX();
                yCros = (int) (xCros * kOne + bOne);
            } else {
                xCros = (int) ((bOne - bTwo) / (kTwo - kOne));
                yCros = (int) (kTwo * (bOne - bTwo) / (kTwo - kOne) + bTwo);
            }
            int minX1 = Math.min(pointOne.getX(), pointTwo.getX());
            int minX2 = Math.min(pointExpOne.getX(), pointExpTwo.getX());
            int maxX1 = Math.max(pointOne.getX(), pointTwo.getX());
            int maxX2 = Math.max(pointExpOne.getX(), pointExpTwo.getX());
            int maxY1 = Math.max(pointOne.getY(), pointTwo.getY());
            int maxY2 = Math.max(pointExpOne.getY(), pointExpTwo.getY());
            int minY1 = Math.min(pointOne.getY(), pointTwo.getY());
            int minY2 = Math.min(pointExpOne.getY(), pointExpTwo.getY());
            if ((xCros < minX1 || xCros > maxX1) && (xCros > minX2 && xCros < maxX2)
                    && (yCros < minY1 || yCros > maxY1) && (yCros > minY2 && yCros < maxY2)) {
                return true;
            }
        }
        return false;
    }

    private Queue<Vertex> getPairs(Figure figure) {
        List<Vertex> vertices = new ArrayList<>(figure.getVertexes());
        Queue<Vertex> pairs = new ArrayDeque<>();
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                pairs.add(vertices.get(i));
                pairs.add(vertices.get(j));
            }
        }
        return pairs;
    }
}
