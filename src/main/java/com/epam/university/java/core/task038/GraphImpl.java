package com.epam.university.java.core.task038;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class GraphImpl implements Graph {

    private Map<VertexImpl, Set<Vertex>> graph = new HashMap<>();
    private int countVertices = 0;
    private boolean hasWrongVertex = false;

    public GraphImpl(int countVertices) {
        this.countVertices = countVertices;
    }

    @Override
    public void createVertex(int id, int x, int y) {
        Vertex vertex = new VertexImpl(id, x, y);
        if (graph.get(vertex) != null) {
            hasWrongVertex = true;
        }
        graph.put(new VertexImpl(id, x, y), new HashSet<>());
    }

    @Override
    public void connectVertices(int fromId, int toId) {
        Vertex fromVertex = getVertexById(fromId);
        Vertex toVertex = getVertexById(toId);
        graph.get(fromVertex).add(toVertex);
    }

    /**
     * Get Vertex by id .
     *
     * @param id it's id from Vertex
     * @return get Vertex
     */
    public Vertex getVertexById(Integer id) {
        Set<VertexImpl> keys = graph.keySet();
        for (Vertex vertex : keys) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        return null;
    }

    public int getCountVertices() {
        return countVertices;
    }

    public void setCountVertices(int countVertices) {
        this.countVertices = countVertices;
    }

    public Map<VertexImpl, Set<Vertex>> getGraph() {
        return graph;
    }

    public boolean isHasWrongVertex() {
        return hasWrongVertex;
    }
}
