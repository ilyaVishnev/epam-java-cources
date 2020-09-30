package com.epam.university.java.core.task012;

import java.util.ArrayList;
import java.util.HashSet;

public class GraphFactoryImpl implements GraphFactory {

    /**
     * Create new Graph instance with designated amount of vertexes.
     *
     * @param vertexesCount vertexes count
     * @return new graph instance
     */
    @Override
    public Graph newInstance(int vertexesCount) {
        if (vertexesCount == 0) {
            throw new IllegalArgumentException();
        }
        GraphImpl graph = new GraphImpl();
        for (int i = 1; i <= vertexesCount; i++) {
            graph.getGraph().put(i, new HashSet<>());
        }
        return graph;
    }
}
