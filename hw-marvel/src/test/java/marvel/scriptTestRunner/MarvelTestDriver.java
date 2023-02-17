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

package marvel.scriptTestRunner;

import graph.Edge;
import graph.Graph;
import graph.Node;
import marvel.MarvelPaths;

import java.io.*;
import java.util.*;

/**
 * This class implements a testing driver which reads test scripts from
 * files for testing Graph, the Marvel parser, and your BFS algorithm.
 */
public class MarvelTestDriver {

    // ***************************
    // ***  JUnit Test Driver  ***
    // ***************************

    private final Map<String, Graph> graphs = new HashMap<String, Graph>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new MarvelTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public MarvelTestDriver(Reader r, Writer w) {
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
                case "LoadGraph":
                    loadGraph(arguments);
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
        Graph g = new Graph();
        graphs.put(graphName, g);
        output.println("created graph " + graphName);
        g.outputTester();
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
        Graph g = graphs.get(graphName);
        g.addNode(new Node(nodeName));
        output.println("added node " + nodeName + " to " + graphName);
        g.outputTester();
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         String edgeLabel) {
        Graph g = graphs.get(graphName);
        Node st = g.getNodeByName(parentName);
        Node ed = g.getNodeByName(childName);
        g.addEdge(new Edge(st, ed, edgeLabel));
        output.println("added edge " + edgeLabel + " from " + parentName + " to " + childName + " in " + graphName);
        g.outputTester();
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        Graph g = graphs.get(graphName);
        Set<Node> nodes = g.getAllNodes();
        Set<String> names = new TreeSet<>();
        for (Node n : nodes) {
            names.add(n.getName());
        }
        output.print(graphName + " contains:");
        for (String s : names) {
            output.print(" " + s);
        }
        output.println();
        g.outputTester();
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
        Graph g = graphs.get(graphName);
        Map<String, List<String>> pairs = g.getChildrenFromNode(g.getNodeByName(parentName));

        for (String nodeName : pairs.keySet()) {
            Collections.sort(pairs.get(nodeName));
        }

        output.print("the children of " + parentName + " in " + graphName + " are:");
        for (String nodeName : pairs.keySet()) {
            for (String labelName : pairs.get(nodeName)) {
                output.print(" " + nodeName + "(" + labelName + ")");
            }
        }
        output.println();
        g.outputTester();
    }

    private void loadGraph(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to LoadGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);
        loadGraph(graphName, fileName);
    }

    private void loadGraph(String graphName, String fileName) {
        Graph g = MarvelPaths.loadGraph(fileName);
        graphs.put(graphName, g);
        output.println("loaded graph " + graphName);
        g.outputTester();
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
        Graph g = graphs.get(graphName);
        Node n1 = g.getNodeByName(nodeName1);
        Node n2 = g.getNodeByName(nodeName2);
        if (n1 == null || n2 == null) {
            if (n1 == null) {
                output.println("unknown: " + nodeName1);
            }
            if (n2 == null) {
                output.println("unknown: " + nodeName2);
            }
        } else {
            output.println("path from " + nodeName1 + " to " + nodeName2 + ":");
            List<Edge> path = MarvelPaths.BFS(g, g.getNodeByName(nodeName1), g.getNodeByName(nodeName2));
            if (path == null) {
                output.println("no path found");
            } else {
                for (Edge e : path) {
                    output.println(e.getStart().getName() + " to " + e.getEnd().getName() + " via " + e.getLabel());
                }
            }
        }
        g.outputTester();
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

