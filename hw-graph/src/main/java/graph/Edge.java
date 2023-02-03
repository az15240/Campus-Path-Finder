package graph;

/**
 * <b>Edge</b> represents a <b>mutable</b> edge in a graph. It connects two <b>Node</b>s from
 * start to end, and directionally pointing from start to end (start and end could
 * possibly be the same nodes). It contains the start node, the end node, and an optional
 * label.
 */
public class Edge {

    /**
     * Start node of the edge
     */
    private Node start;

    /**
     * End node of the edge
     */
    private Node end;

    /**
     * Optional label of the edge, in String
     */
    private String label;

    /**
     * Constructs a new edge with default label.
     * @param st start node of the edge
     * @param ed end node of the edge
     * @spec.effects Constructs a new edge with start node, end node, and default label.
     */
    public Edge(Node st, Node ed) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Constructs a new node with given label l.
     * @param st start node of the edge
     * @param ed end node of the edge
     * @param l label of the edge
     * @spec.effects Constructs a new edge with start node, end node, and label l.
     */
    public Edge(Node st, Node ed, String l) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns the start node of the current edge.
     *
     * @return the start node of the current edge.
     */
    public Node getStart() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Changes the start node of the current edge to st.
     *
     * @param st the given start node
     * @spec.modifies start
     */
    public void setStart(Node st) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns the end node of the current edge.
     *
     * @return the end node of the current edge.
     */
    public Node getEnd() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Changes the end node of the current edge to ed.
     *
     * @param ed the given end node
     * @spec.modifies end
     */
    public void setEnd(Node ed) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns the label of the current edge.
     *
     * @return the label of the current edge.
     */
    public String getLabel() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Changes the label of the current edge to l.
     *
     * @param l the given label
     * @spec.modifies label
     */
    public void setLabel(String l) {
        throw new RuntimeException("Not implemented yet!");
    }
}
