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
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;

import java.util.*;

import pathfinder.parser.CampusPathsParser;

/**
 * <b>CampusMap</b> represents a UW campus map. It consists of a list of buildings and a list of paths
 * inside the campus, both have their own unique types. It supports the finding of a shortest path between
 * two points in the campus. The most essential data it uses is a Graph, with Point as its generic type of
 * Node and Double as its generic type of Edge (for the label).
 */
public class CampusMap implements ModelAPI {

    // AF(this) =
    //      a list of campus buildings => buildings
    //      a map of buildings' short names to long names => buildingNamesMap
    //      a list of paths in campus => campusPaths
    //      the graph of the campus, containing all nodes and edges in the paths => graph

    // Rep Invariant:
    //      buildings != null &&
    //      buildingNamesMap != null &&
    //      campusPaths != null &&
    //      graph != null

    private List<CampusBuilding> buildings;
    private Map<String, String> buildingNamesMap;
    private List<CampusPath> campusPaths;
    private Graph<Point, Double> graph;

    /**
     * Constructs a new CampusMap object and initializes all private fields.
     */
    public CampusMap() {
        buildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
        campusPaths = CampusPathsParser.parseCampusPaths("campus_paths.csv");
        buildingNamesMap = new HashMap<>();
        for (CampusBuilding cb : buildings) {
            buildingNamesMap.put(cb.getShortName(), cb.getLongName());
        }
        graph = new Graph<>();
        for (CampusPath cp : campusPaths) {
            Point p1 = new Point(cp.getX1(), cp.getY1());
            Point p2 = new Point(cp.getX2(), cp.getY2());
            Node<Point> n1 = new Node<>(p1);
            Node<Point> n2 = new Node<>(p2);
            Edge<Point, Double> e = new Edge<>(n1, n2, cp.getDistance());
            if (!graph.containsNode(n1)) {
                graph.addNode(n1);
            }
            if (!graph.containsNode(n2)) {
                graph.addNode(n2);
            }
            graph.addEdge(e);
        }
        checkRep();
    }

    // Throws an exception if the representation invariant is violated.
    private void checkRep() {
        assert (buildings != null);
        assert (buildingNamesMap != null);
        assert (campusPaths != null);
        assert (graph != null);
    }

    /**
     * Checks if the given short name exists in the campus buildings.
     *
     * @param shortName The short name of a building to query.
     * @return {@literal true} iff the short name provided exists in this campus map.
     */
    @Override
    public boolean shortNameExists(String shortName) {
        return buildingNamesMap.containsKey(shortName);
    }

    /**
     * Gets the long name format for a given short name for the campus buildings.
     *
     * @param shortName The short name of a building to look up.
     * @return The long name of the building corresponding to the provided short name.
     * @throws IllegalArgumentException if the short name provided does not exist.
     */
    @Override
    public String longNameForShort(String shortName) {
        if (!shortNameExists(shortName)) {
            throw new IllegalArgumentException("the short name provided does not exist!");
        }
        return buildingNamesMap.get(shortName);
    }

    /**
     * Gets a mapping from all the buildings' short names to their long names in this campus map.
     *
     * @return A mapping from all the buildings' short names to their long names in this campus map.
     */
    @Override
    public Map<String, String> buildingNames() {
        return buildingNamesMap;
    }

    /**
     * Finds the shortest path, by distance, between the two provided buildings.
     *
     * @param startShortName The short name of the building at the beginning of this path.
     * @param endShortName   The short name of the building at the end of this path.
     * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
     * if none exists.
     * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
     *                                  {@literal null}, or not valid short names of buildings in
     *                                  this campus map.
     */
    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        if (startShortName == null || endShortName == null || !shortNameExists(startShortName) || !shortNameExists(endShortName)) {
            throw new IllegalArgumentException("startBuilding or endBuilding is null, or not valid short names of buildings in this campus map");
        }
        Node<Point> start = null;
        Node<Point> end = null;
        for (CampusBuilding cb : buildings) {
            if (cb.getShortName().equals(startShortName)) {
                start = new Node<>(new Point(cb.getX(), cb.getY()));
            }
            if (cb.getShortName().equals(endShortName)) {
                end = new Node<>(new Point(cb.getX(), cb.getY()));
            }
        }
        return Dijkstra(graph, start, end);
    }

    /**
     * Dijkstra algorithm, to find the shortest path between two Nodes in a Graph.
     * If no path is found or the two given nodes are the same one, then it returns null.
     *
     * @spec.requires g != null &amp;&amp; start != null &amp;&amp; end != null
     *                &amp;&amp; start is in g &amp;&amp; end is in g
     * @param g the given graph
     * @param start the given start node
     * @param dest the given destination node
     * @return a path connecting these two nodes,
     *         or null if no path is found or the two given nodes are the same one
     * @param <E> generic type for the Nodes
     */
    public static <E extends Comparable<E>> Path<E> Dijkstra(Graph<E, Double> g, Node<E> start, Node<E> dest) {
        PriorityQueue<Path<E>> active = new PriorityQueue<>();
        Set<Node<E>> finished = new HashSet<>();
        Path<E> temp = new Path<>(start.getValue());
        active.add(temp);

        while (!active.isEmpty()) {
            Path<E> minPath = active.remove();
            E minDestPoint = minPath.getEnd();
            Node<E> minDest = new Node<>(minDestPoint);
            if (minDest.equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
            Set<Edge<E, Double>> children = g.getChildrenFromNodeReturnValueSet(minDest);
            for (Edge<E, Double> edge : children) {
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
