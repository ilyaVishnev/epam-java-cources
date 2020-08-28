package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FigureImpl implements Figure {

    private List<Vertex> vertices = new ArrayList<>();

    /**
     * Add vertex to figure with designated coordinates.
     *
     * @param vertex vertex to add
     */
    @Override
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    /**
     * Get all vertexes of figure.
     *
     * @return collection of vertexes
     */
    @Override
    public Collection<Vertex> getVertexes() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }
}
