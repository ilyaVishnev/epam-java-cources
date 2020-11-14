package com.epam.university.java.core.task038;

import java.util.Objects;

public class VertexImpl implements Vertex {

    private int x;
    private int y;
    private int id;

    /**
     * Constructor with id and coordinates .
     *
     * @param id it's id for Vertex
     * @param x  coordinate x
     * @param y  coordinate y
     */
    public VertexImpl(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VertexImpl vertex = (VertexImpl) o;
        return x == vertex.x
                && y == vertex.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
