package graph;

import java.util.*;

/**
 * <b>Graph</b> represents a <b>mutable</b> graph. It consists of a set of <b>Node</b>s and
 * a set of <b>Edge</b>s connecting the nodes. It also supports various methods in manipulating
 * the graph. Note that this graph can contain multiple edges with the same start and end nodes.
 * All Nodes must have different names.
 */
public class Graph {

    // Abstraction Function:
    // Graph g, represents a graph that contains a set of Nodes and a set of Edges connecting
    // the nodes. The graph can contain multiple edges with the same start and end nodes.
    // In a graph, nodes is the set of nodes in the graph while edges is the set of edges in it.
    //
    // Representation Invariant:
    // this != null && nodes != null && edges != null
    // && the start and end Node of each Edge in edges are in nodes
    // or in pseudocode: nodes.contains(e.start) && nodes.contains(e.end) for all Edge e in edges
    // && all Nodes must have different names
    // or in pseudocode: n1.name.equals(n2.name) is false for all different Nodes n1, n2 in nodes

    /**
     * Debug variable: set to false on submission
     */
    private static final boolean DEBUG = false;

    /**
     * A set of nodes in the graph
     */
    private Set<Node> nodes;

    /**
     * A map containing all edges in the graph
     * With the same start node as the key, it maps to a set of edges with same start
     * node as the key.
     */
    private Map<Node, Set<Edge>> edges;

    /**
     * Constructs a new, empty graph.
     *
     * @spec.effects Constructs a new, empty graph with its nodes and edges initialized.
     */
    public Graph() {
        nodes = new HashSet<>();
        edges = new HashMap<>();
        checkRep();
    }

    // Throws an exception if the representation invariant is violated.
    private void checkRep() {
        /*
        assert (this != null);
        assert (nodes != null);
        assert (edges != null);
        Set<String> names = new HashSet<>();
        for (Node n : nodes) {
            assert (!names.contains(n.getName()));
            names.add(n.getName());
        }
        if (DEBUG) {
            // Set<String> labels = new HashSet<>();
            for (Node n : edges.keySet()) {
                Set<Edge> eset = edges.get(n);
                for (Edge e : eset) {
                    assert (nodes.contains(e.getStart()));
                    assert (nodes.contains(e.getEnd()));
                    // assert (!labels.contains(e.getLabel()));
                    // labels.add(e.getLabel());
                }
            }
        }
        */
    }

    /**
     * Adds a node into the graph. If the node exists, then no new effects.
     *
     * @spec.requires node != null
     * @param node the given node to be added.
     * @spec.modifies nodes
     * @spec.effects If the given node does not exist, then add a new node into the graph.
     */
    public void addNode(Node node) {
        // if (getNodeByName(node.getName()) == null) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
        checkRep();

//        for (Node n : nodes) {
//            if (n.getName().equals(node.getName())) {
//                return; // the same node exists, do nothing
//            }
//        }
//        nodes.add(node);
//        checkRep();
    }

    /**
     * Adds an edge into the graph. If the same edge exists, then no new effects. The
     * start node and end node of the given edge must exist in the graph.
     *
     * @spec.requires edge != null &amp;&amp; the start node is in the graph &amp;&amp; the end node is in the graph
     * @param edge the given edge to be added.
     * @spec.modifies edges
     * @spec.effects If the given edge does not exist, then add the edge into the graph.
     */
    public void addEdge(Edge edge) {
        Node st = edge.getStart();
        if (nodes.contains(st) && nodes.contains(edge.getEnd())) {
            if (!edges.containsKey(st)) { // Key not found, so create a new Set
                Set<Edge> theEdges = new HashSet<>();
                edges.put(st, theEdges);
                theEdges.add(edge);
            } else { // Key is found, then just add the edge to the Mapped Set
                Set<Edge> theEdges = edges.get(st);
                theEdges.add(edge);
            }
        }
        checkRep();
    }

    /**
     * Returns a set of all nodes in the graph.
     *
     * @return a set of all nodes in the graph.
     */
    public Set<Node> getAllNodes() {
        return nodes;
    }

    /**
     * Returns a map of all edges in the graph.
     *
     * @return a map of all edges in the graph.
     */
    public Map<Node, Set<Edge>> getAllEdges() {
        return edges;
    }

    /**
     * Returns a node of the given name, or null if the name is not found.
     *
     * @spec.requires name != null
     * @param name the given name
     * @return the node of the given name, or null if the name is not found.
     */
    public Node getNodeByName(String name) {
        for (Node n : nodes) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }

    /**
     * Returns an edge of the given label, or null if the label is not found.
     *
     * @spec.requires label != null
     * @param label the given label
     * @return the edge of the given label, or null if the label is not found.
     */
    public Edge getEdgeByLabel(String label, Node start, String end) {
        // Node n = getNodeByName(start);
        // Set<Edge> eset = edges.get(n);

        // for (Node n : edges.keySet()) {
            Set<Edge> eset = edges.get(start);
            for (Edge e : eset) {
                if (e.getLabel().equals(label) && /*e.getStart().getName().equals(start) && */e.getEnd().getName().equals(end)) {
                    return e;
                }
            }
        // }
        return null;
    }

    /**
     * Returns a map of string-string pairs such that for each pair, the first string is the label of the path
     * towards the child node, the second string is the name of the child of the given node.
     * Children of a node means nodes being the direct end node from an edge starting from the given node.
     * Or returns an empty set if the node is not found in the graph or if the node has no children.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return a map of string-string pairs that denotes the label of the path paired with the children name
     * of the given node, or an empty map if the node is not found in the graph or if the node has no children.
     */
    public Map<String, List<String>> getChildrenFromNode(Node node) {
        Map<String, List<String>> children = new TreeMap<>();
        if (edges.get(node) == null) {
            return children;
        }
        for (Edge e : edges.get(node)) {
            String childName = e.getEnd().getName();
            String label = e.getLabel();
            if (!children.containsKey(childName)) {
                List<String> theEdges = new ArrayList<>();
                theEdges.add(label);
                children.put(childName, theEdges);
            } else {
                List<String> theEdges = children.get(childName);
                theEdges.add(label);
            }
        }
        return children;
    }

    public void outputTester() {
/*
        System.out.print("\n||| CALLING outputTester |||\nAll nodes:");
        for (Node n : nodes) {
            System.out.print(" " + n.getName());
        }
        System.out.print("\n");
        for (Node n : edges.keySet()) {
            System.out.println("All children of node " + n.getName() + " are: ");
            for (Edge e : edges.get(n)) {
                System.out.println(" " + e.getEnd().getName() + " via " + e.getLabel());
            }
        }
*/
    }
}
