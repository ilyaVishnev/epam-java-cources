package com.epam.university.java.core.task012;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Task012Impl implements Task012 {
    /**
     * Create new Graph instance and execute collection of actions. Return
     * the result graph instance.
     *
     * @param sourceGraph initial graph instance
     * @param actions     collection of actions
     * @return updated graph instance
     */
    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (GraphAction graphAction : actions) {
            graphAction.run(sourceGraph);
        }
        return sourceGraph;
    }

    /**
     * Check is there path in <code>graph</code> between <code>from</code> and
     * <code>to</code> vertexes.
     *
     * @param graph graph instance
     * @param from  source vertex
     * @param to    target vertex
     * @return is path exists
     */
    @Override
    public boolean pathExists(Graph graph, int from, int to) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        Set<Integer> vertexes = new HashSet<>(graph.getAdjacent(from));
        return vertexes.contains(to);
    }
}
