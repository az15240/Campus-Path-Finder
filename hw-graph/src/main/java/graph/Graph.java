package graph;

import java.util.*;

/**
 * <b>Graph</b> represents a <b>mutable</b> graph. It consists of a set of <b>Node</b>s and
 * a set of <b>Edge</b>s connecting the nodes. It also supports various methods in manipulating
 * the graph. Note that this graph can contain multiple edges with the same start and end nodes.
 * All Nodes must have different names. It uses generics on type of Nodes and Edges. Note that
 * the type of Nodes are Comparable.
 *
 * @param <E1> Generic type for Nodes.
 * @param <E2> Generic type for Edges.
 */
public class Graph<E1 extends Comparable<E1>, E2> {

    // Abstraction Function:
    // Graph g, represents a graph that contains a set of Nodes and a set of Edges connecting
    // the nodes. The graph can contain multiple edges with the same start and end nodes.
    // In a graph, nodes is the set of nodes in the graph while edges is the set of edges in it.
    //
    // Representation Invariant:
    // this != null && nodes != null && edges != null
    // && the start and end Node of each Edge in edges are in nodes
    // or in pseudocode: nodes.contains(e.start) && nodes.contains(e.end) for all Edge e in edges
    // && all Nodes must have different values
    // or in pseudocode: n1.value.equals(n2.value) is false for all different Nodes n1, n2 in nodes

    /**
     * Debug variable: set to false on submission
     */
    private static final boolean DEBUG = false;

    /**
     * A set of nodes in the graph
     */
    private Set<Node<E1>> nodes;

    /**
     * A map containing all edges in the graph
     * With the same start node as the key, it maps to a set of edges with same start
     * node as the key.
     */
    private Map<Node<E1>, Set<Edge<E1, E2>>> edges;

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
        assert (this != null);
        assert (nodes != null);
        assert (edges != null);
        if (DEBUG) {
            Set<E1> names = new HashSet<>();
            for (Node<E1> n : nodes) {
                assert (!names.contains(n.getValue()));
                names.add(n.getValue());
            }
            for (Node<E1> n : edges.keySet()) {
                Set<Edge<E1, E2>> eset = edges.get(n);
                for (Edge<E1, E2> e : eset) {
                    assert (nodes.contains(e.getStart()));
                    assert (nodes.contains(e.getEnd()));
                }
            }
        }
    }

    /**
     * Adds a node into the graph. If the node exists, then no new effects.
     *
     * @param node the given node to be added.
     * @spec.requires node != null
     * @spec.modifies nodes
     * @spec.effects If the given node does not exist, then add a new node into the graph.
     */
    public void addNode(Node<E1> node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
        checkRep();
    }

    /**
     * Adds an edge into the graph. If the same edge exists, then no new effects. The
     * start node and end node of the given edge must exist in the graph.
     *
     * @param edge the given edge to be added.
     * @spec.requires edge != null &amp;&amp; the start node is in the graph &amp;&amp; the end node is in the graph
     * @spec.modifies edges
     * @spec.effects If the given edge does not exist, then add the edge into the graph.
     */
    public void addEdge(Edge<E1, E2> edge) {
        Node<E1> st = edge.getStart();
        if (nodes.contains(st) && nodes.contains(edge.getEnd())) {
            if (!edges.containsKey(st)) { // Key not found, so create a new Set
                Set<Edge<E1, E2>> theEdges = new HashSet<>();
                edges.put(st, theEdges);
                theEdges.add(edge);
            } else { // Key is found, then just add the edge to the Mapped Set
                Set<Edge<E1, E2>> theEdges = edges.get(st);
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
    public Set<Node<E1>> getAllNodes() {
        return nodes;
    }

    /**
     * Returns a map of all edges in the graph.
     *
     * @return a map of all edges in the graph.
     */
    public Map<Node<E1>, Set<Edge<E1, E2>>> getAllEdges() {
        return edges;
    }

    /**
     * Checks if a given node is in this.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return true if node is in this, false otherwise.
     */
    public boolean containsNode(Node<E1> node) {
        return nodes.contains(node);
    }

    /**
     * Returns a map of string-list of string pairs such that for each pair, the key string is the name of the
     * child node, the list of string is the list of labels of the edges towards the child node.
     * Children of a node means nodes being the direct end node from an edge starting from the given node.
     * Or returns an empty set if the node is not found in the graph or if the node has no children.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return a map of string-list of string pairs that denotes the name of the child node paired with the list
     * of labels of the edges towards the child node, or an empty map if the node is not found in the graph or if
     * the node has no children.
     */
    public Map<E1, List<E2>> getChildrenFromNode(Node<E1> node) {
        Map<E1, List<E2>> children = new TreeMap<>();
        if (edges.get(node) == null) {
            return children;
        }
        for (Edge<E1, E2> e : edges.get(node)) {
            E1 childName = e.getEnd().getValue();
            E2 label = e.getLabel();
            if (!children.containsKey(childName)) {
                List<E2> theEdges = new ArrayList<>();
                theEdges.add(label);
                children.put(childName, theEdges);
            } else {
                List<E2> theEdges = children.get(childName);
                theEdges.add(label);
            }
        }
        return children;
    }

    /**
     * Returns a set of Edges representing and all edges starting from the given node.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return a set of Edges representing and all edges starting from the given node.
     */
    public Set<Edge<E1, E2>> getChildrenFromNodeReturnValueSet(Node<E1> node) {
        if (edges.get(node) == null) {
            return new HashSet<>();
        }
        return edges.get(node);
    }

    /**
     * A String representation of this, useful for testing.
     *
     * @return a String representation of this
     */
    public String toString() {
        String text = "    Graph contains:\n";
        for (Node<E1> nd : nodes) {
            text += "   " + nd.toString() + "\n";
        }
        for (Node<E1> nd : edges.keySet()) {
            Set<Edge<E1, E2>> eds = edges.get(nd);
            for (Edge<E1, E2> ed : eds) {
                text += "   " + ed.toString() + "\n";
            }
        }
        text += "\n";
        return text;
    }

    // Private helper method, to help testing and debugging
    private void outputTester() {
        System.out.print("\n||| CALLING outputTester |||\nAll nodes:");
        for (Node<E1> n : nodes) {
            System.out.print(" " + n.getValue());
        }
        System.out.print("\n");
        for (Node<E1> n : edges.keySet()) {
            System.out.println("All children of node " + n.getValue() + " are: ");
            for (Edge<E1, E2> e : edges.get(n)) {
                System.out.println(" " + e.getEnd().getValue() + " via " + e.getLabel());
            }
        }
    }
}
