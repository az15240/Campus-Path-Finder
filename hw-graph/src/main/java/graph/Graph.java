package graph;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>Graph</b> represents a <b>mutable</b> graph. It consists of a set of <b>Node</b>s and
 * a set of <b>Edge</b>s connecting the nodes. It also supports various methods in manipulating
 * the graph.
 */
public class Graph {

    /**
     * A set of nodes in the graph, in sorted order
     */
    private Set<Node> nodes;

    /**
     * A map containing all edges in the graph, with edges in sorted order
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
        throw new RuntimeException("Not implemented yet!");
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
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Removes a node from a graph. Also remove all edges starting from or ending at the
     * given node. If the node does not exist, then no new effects.
     *
     * @spec.requires node != null
     * @param node the given node to be removed.
     * @spec.modifies nodes, edges
     * @spec.effects If the given node does exist, then remove the given node and remove all
     *               edges starting from or ending at the given node.
     */
    public void removeNode(Node node) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Adds an edge into the graph. If the same edge exists, then no new effects. The
     * start node and end node of the given edge must exist in the graph.
     *
     * @spec.requires edge != null &amp;&amp; nodes.contains(edge.start) &amp;&amp; nodes.contains(edge.end)
     * @param edge the given edge to be added.
     * @spec.modifies edges
     * @spec.effects If the given edge does not exist, then add the edge into the graph.
     */
    public void addEdge(Edge edge) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Removes an edge from a graph. If the same edge does not exist, then no new effects.
     * The start node and end node of the given edge must exist in the graph.
     *
     * @spec.requires edge != null &amp;&amp; nodes.contains(edge.start) &amp;&amp; nodes.contains(edge.end)
     * @param edge the given edge to be removed.
     * @spec.modifies edges
     * @spec.effects If the given edge does exist, then remove the edge from the graph.
     */
    public void removeEdge(Edge edge) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of all nodes in the graph.
     *
     * @return a set of all nodes in the graph.
     */
    public Set<Node> getAllNodes() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a map of all edges in the graph.
     *
     * @return a map of all edges in the graph.
     */
    public Map<Node, Set<Edge>> getAllEdges() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a node of the given name, or null if not found.
     *
     * @spec.requires name != null
     * @param name the given name
     * @return the node of the given name, or null if not found.
     */
    public Node getNodeByName(String name) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns an edge of the given label, or null if not found.
     *
     * @spec.requires label != null
     * @param label the given label
     * @return the edge of the given label, or null if not found.
     */
    public Edge getEdgeByLabel(String label) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of paths from start node to end node, or an empty set if either
     * node is not found in the graph. A path is a list of edges ⟨node1,node2⟩,
     * ⟨node2,node3⟩, ⟨node3,node4⟩, .... In other words, a path is an ordered list of
     * edges, where an edge to some node is immediately followed by an edge from that node.
     *
     * @spec.requires st != null &amp;&amp; ed != null
     * @param st start node
     * @param ed end node
     * @return the set of paths from start node to end node, or an empty set if either node
     * is not found in the graph.
     */
    public Set<List<Edge>> getEdgesByTwoNodes(Node st, Node ed) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of nodes that are children of the given node. Children of a node
     * means nodes being the direct end node from an edge starting from the given node.
     * Or returns an empty set if the node is not found in the graph.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return a set of nodes that are children of the given node, or an empty set if the
     * node is not found in the graph.
     */
    public Set<Node> getChildrenFromNode(Node node) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of nodes that are parents of the given node. Parents of a node means
     * nodes being the direct start node of an edge pointing to the given node as end node.
     * Or returns an empty set if the node is not found in the graph.
     *
     * @spec.requires node != null
     * @param node the given node
     * @return a set of nodes that are parents of the given node, or an empty set if the
     * node is not found in the graph.
     */
    public Set<Node> getParentsFromNode(Node node) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of nodes that are neighbors of the given node. Neighbors of a node means
     * nodes being the direct start node or end node of an edge pointing to or starting from
     * the given node. Or returns an empty set if the node is out found in the graph.
     *
     * @param node the given node
     * @return a set of nodes that are neighbors of the given node, or an empty set if the
     * node is not found in the graph.
     */
    public Set<Node> getNeighborsFromNode(Node node) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns a set of nodes that are accessible through an ordered list of edges starting
     * from the given node. Or returns an empty set if the node is out found in the graph.
     *
     * @param node the given node
     * @return a set of nodes that are accessible through an ordered list of edges starting
     *         from the given node. Or returns an empty set if the node is out found.
     */
    public Set<Node> getAllAccessibleNodes(Node node) {
        throw new RuntimeException("Not implemented yet!");
    }
}
