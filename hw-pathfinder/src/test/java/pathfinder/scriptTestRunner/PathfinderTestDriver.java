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

package pathfinder.scriptTestRunner;

import graph.Edge;
import graph.Graph;
import graph.Node;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    private final Map<String, Graph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new MarvelTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        // See GraphTestDriver as an example.
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        Graph<String, Double> g = new Graph<>();
        graphs.put(graphName, g);
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph<String, Double> g = graphs.get(graphName);
        g.addNode(new Node<>(nodeName));
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabelString = arguments.get(3);

        try {
            double edgeLabel = Double.parseDouble(edgeLabelString);
            addEdge(graphName, parentName, childName, edgeLabel);
        } catch (NumberFormatException e) {
            output.println("Provided label is not a double!");
        }
    }

    private void addEdge(String graphName, String parentName, String childName,
                         double edgeLabel) {
        Graph<String, Double> g = graphs.get(graphName);
        // Node<String> st = g.getNodeByName(parentName);
        // Node<String> ed = g.getNodeByName(childName);
        Node<String> st = new Node<>(parentName);
        Node<String> ed = new Node<>(childName);
        g.addEdge(new Edge<>(st, ed, edgeLabel));
        String edgeLabelString = String.format("%.3f", edgeLabel);
        output.println("added edge " + edgeLabelString + " from " + parentName + " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        Graph<String, Double> g = graphs.get(graphName);
        Set<Node<String>> nodes = g.getAllNodes();
        Set<String> names = new TreeSet<>();
        for (Node<String> n : nodes) {
            names.add(n.getValue());
        }
        output.print(graphName + " contains:");
        for (String s : names) {
            output.print(" " + s);
        }
        output.println();
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        Graph<String, Double> g = graphs.get(graphName);
        Map<String, List<Double>> pairs = g.getChildrenFromNode(new Node<>(parentName)/*g.getNodeByName(parentName)*/);

        for (String nodeName : pairs.keySet()) {
            Collections.sort(pairs.get(nodeName));
        }

        output.print("the children of " + parentName + " in " + graphName + " are:");
        for (String nodeName : pairs.keySet()) {
            for (double labelName : pairs.get(nodeName)) {
                String LabelNameString = String.format("%.3f", labelName);
                output.print(" " + nodeName + "(" + LabelNameString + ")");
            }
        }
        output.println();
    }

    private void findPath(List<String> arguments) {
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName1 = arguments.get(1);
        String nodeName2 = arguments.get(2);
        findPath(graphName, nodeName1, nodeName2);
    }

    private void findPath(String graphName, String nodeName1, String nodeName2) {
        Graph<String, Double> g = graphs.get(graphName);
        // Node<String> n1 = g.getNodeByName(nodeName1);
        // Node<String> n2 = g.getNodeByName(nodeName2);
        Node<String> n1 = new Node<>(nodeName1);
        Node<String> n2 = new Node<>(nodeName2);
        if (!g.getAllNodes().contains(n1) || !g.getAllNodes().contains(n2)) {
            if (!g.getAllNodes().contains(n1)) {
                output.println("unknown: " + nodeName1);
            }
            if (!g.getAllNodes().contains(n2)) {
                output.println("unknown: " + nodeName2);
            }
        } else {
            output.println("path from " + nodeName1 + " to " + nodeName2 + ":");
            Path<String> path = CampusMap.Dijkstra(g, n1, n2);
            if (path == null) {
                output.println("no path found");
            } else {
                double totalCost = 0.0;
                for (Path<String>.Segment e : path) {
                    String costString = String.format("%.3f", e.getCost());
                    output.println(e.getStart() + " to " + e.getEnd() + " with weight " + costString); // TODO: test this
                    totalCost += e.getCost();
                }
                String totalCostString = String.format("%.3f", totalCost);
                output.println("total cost: " + totalCostString);
            }
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
