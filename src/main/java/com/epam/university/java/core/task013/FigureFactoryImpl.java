package com.epam.university.java.core.task013;

import java.util.ArrayList;

public class FigureFactoryImpl implements FigureFactory {
    @Override
    public Figure newInstance(int vertexCount) {
        if (vertexCount < 3) {
            throw new IllegalArgumentException();
        }
        FigureImpl figure = new FigureImpl();
        figure.setSize(vertexCount);
        return figure;
    }

    @Override
    public Vertex newInstance(int x, int y) {
        Vertex vertex = new VertexImpl();
        vertex.setX(x);
        vertex.setY(y);
        return vertex;
    }
}
