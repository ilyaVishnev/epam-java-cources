package com.epam.university.java.core.task012;


import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class GraphImpl implements Graph {

    private Map<Integer, Set<Integer>> graph = new HashMap<>();

    /**
     * Create edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     */
    @Override
    public void createEdge(int from, int to) {
        if (graph.get(from) != null) {
            graph.get(from).add(to);
        } else {
            graph.put(from, new HashSet<>(Arrays.asList(to)));
        }
        if (graph.get(to) != null) {
            graph.get(to).add(from);
        } else {
            graph.put(to, new HashSet<>(Arrays.asList(from)));
        }
    }

    /**
     * Check is there edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     * @return is there edge between vertexes
     */
    @Override
    public boolean edgeExists(int from, int to) {
        return graph.get(from) != null ? graph.get(from).contains(to) : false;
    }

    /**
     * Remove edge between <code>from</code> and <code>to</code> vertexes.
     *
     * @param from vertex edge starts from
     * @param to   vertex edge ends with
     */
    @Override
    public void removeEdge(int from, int to) {
        graph.get(from).remove(to);
        graph.get(to).remove(from);
    }

    /**
     * Get collection of vertexes which is available from <code>from</code>.
     *
     * @param from vertex from
     * @return collection of available vertexes
     */
    @Override
    public Collection<Integer> getAdjacent(int from) {
        Set<Integer> alreadyExist = new HashSet<>(Arrays.asList(from));
        return searching(from, alreadyExist);
    }

    private Collection<Integer> searching(int from, Set<Integer> alreadyExist) {
        Set<Integer> vertexes = new HashSet<Integer>();
        Iterator<Integer> iterator = graph.get(from).iterator();
        while (iterator.hasNext()) {
            Integer vertex = iterator.next();
            if (!alreadyExist.contains(vertex)) {
                alreadyExist.add(vertex);
                vertexes.add(vertex);
                vertexes.addAll(searching(vertex, alreadyExist));
            }
        }
        return vertexes;
    }

    public Map<Integer, Set<Integer>> getGraph() {
        return graph;
    }

    public void setGraph(Map<Integer, Set<Integer>> graph) {
        this.graph = graph;
    }
}
