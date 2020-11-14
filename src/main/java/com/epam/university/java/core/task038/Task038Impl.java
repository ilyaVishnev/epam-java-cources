package com.epam.university.java.core.task038;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Task038Impl implements Task038 {

    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        if (sourceGraph == null
                || actions == null
                || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (GraphAction action : actions) {
            action.run(sourceGraph);
            if (((GraphImpl) sourceGraph).isHasWrongVertex()) {
                throw new IllegalArgumentException();
            }
        }
        return sourceGraph;
    }

    @Override
    public Collection<Vertex> getShortestPath(Graph graph, int startId, int endId) {
        if (graph == null) {
            throw new IllegalArgumentException();
        }
        GraphImpl myGraph = (GraphImpl) graph;
        List<List<Vertex>> vertexList = new ArrayList<>();
        List<Vertex> vertices = new ArrayList<>();
        getPath(myGraph, startId, endId, vertexList, vertices);
        List<Vertex> min = new ArrayList<>();
        double minSum = Double.MAX_VALUE;
        for (List<Vertex> list : vertexList) {
            double myMin = 0;
            for (int i = 1; i < list.size(); i++) {
                myMin += Math.hypot(list.get(i).getX() - list.get(i - 1).getX(),
                        list.get(i).getY() - list.get(i - 1).getY());
            }
            if (Double.compare(myMin, minSum) < 0) {
                minSum = myMin;
                min = list;
            }
        }
        return min;
    }

    private void getPath(GraphImpl graph, int startId, int endId, List<List<Vertex>> verticesList,
                         List<Vertex> vertices) {
        vertices.add(graph.getVertexById(startId));
        Iterator<Vertex> iterator = graph.getGraph().get(graph.getVertexById(startId)).iterator();
        while (iterator.hasNext()) {
            Vertex vertex = iterator.next();
            if (vertex.getId() == endId) {
                vertices.add(vertex);
                verticesList.add(vertices);
                vertices = new ArrayList<>(vertices);
                vertices.remove(vertex);
            } else {
                getPath(graph, vertex.getId(), endId, verticesList, new ArrayList<>(vertices));
            }
        }
    }
}
