/*
 * Copyright (C) 2023 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2023 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import graph.Edge;
import graph.Graph;
import graph.Node;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import java.util.*;

/**
 * TODO: comment
 *
 */
public class CampusMap<E> implements ModelAPI<E> {

    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public Path<E> findShortestPath(String startShortName, String endShortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        throw new RuntimeException("Not Implemented Yet");
    }

    public static <E extends Comparable<E>> Path<E> Dijkstra(Graph<E, Double> g, Node<E> start, Node<E> dest) {
        System.out.println(g);
        System.out.println(start);
        System.out.println(dest);

        PriorityQueue<Path<E>> active = new PriorityQueue<>();
        Set<Node<E>> finished = new HashSet<>();
        Path<E> temp = new Path<>(start.getValue());
        active.add(temp);

        while (!active.isEmpty()) {
            Path<E> minPath = active.remove();
            System.out.println("minPath : " + minPath.toString());
            E minDestPoint = minPath.getEnd();
            Node<E> minDest = new Node<>(minDestPoint);
            if (minDest.equals(dest)) {
                System.out.println("Equals dest!");
                return minPath;
            }
            if (finished.contains(minDest)) {
                System.out.println("Already finished!");
                continue;
            }
            Set<Edge<E, Double>> children = g.getChildrenFromNodeReturnValueSet(minDest);
            System.out.println(children.size());
            for (Edge<E, Double> edge : children) {
                System.out.println("In its children, edge: " + edge);
                E child = edge.getEnd().getValue();
                if (!finished.contains(edge.getEnd())) {
                    Path<E> newPath = minPath.extend(child, edge.getLabel());
                    active.add(newPath);
                }
            }
            finished.add(minDest);
        }
        return null;
    }
}
