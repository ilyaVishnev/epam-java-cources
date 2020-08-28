package com.epam.university.java.core.task013;

import java.util.ArrayList;

public class FigureFactoryImpl implements FigureFactory {
    @Override
    public Figure newInstance(int vertexCount) {
        FigureImpl figure = new FigureImpl();
        figure.setVertices(new ArrayList<>(vertexCount));
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
