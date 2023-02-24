package graph;

/**
 * <b>Edge</b> represents a <b>mutable</b> edge in a graph. It connects two <b>Node</b>s from
 * start to end, and directionally pointing from start to end (start and end could
 * possibly be the same nodes). It contains the start node, the end node, and an optional
 * label. It uses generic types on the type of Node and the type of connecting label.
 * Two <b>Edge</b>s are considered equal if they have the same start node, end node, and same label.
 *
 * @param <E1> Generic type for Nodes.
 * @param <E2> Generic type for label.
 */
public class Edge<E1, E2> {

    // Abstraction Function:
    // Edge e, represents an edge in a graph. It connects two not necessarily different nodes.
    // In an Edge, start is the start node of the edge and end is the end node of the edge.
    // label is the optional label of the edge.
    //
    // Representation Invariant:
    // this != null && start != null && end != null

    /**
     * Start node of the edge
     */
    private Node<E1> start;

    /**
     * End node of the edge
     */
    private Node<E1> end;

    /**
     * Label of the edge, in generics
     */
    private E2 label;

    /**
     * Constructs a new edge with default empty label.
     * @param st start node of the edge
     * @param ed end node of the edge
     * @spec.requires st != null &amp;&amp; ed != null
     * @spec.effects Constructs a new edge with start node, end node, and default empty label.
     */
    public Edge(Node<E1> st, Node<E1> ed) {
        start = st;
        end = ed;
        label = null;
        checkRep();
    }

    /**
     * Constructs a new node with given label l.
     * @param st start node of the edge
     * @param ed end node of the edge
     * @param l label of the edge
     * @spec.requires st != null &amp;&amp; ed != null
     * @spec.effects Constructs a new edge with start node, end node, and label l.
     */
    public Edge(Node<E1> st, Node<E1> ed, E2 l) {
        start = st;
        end = ed;
        label = l;
        checkRep();
    }

    // Throws an exception if the representation invariant is violated.
    private void checkRep() {
        assert (this != null);
        assert (start != null);
        assert (end != null);
    }

    /**
     * Returns the start node of the current edge.
     *
     * @return the start node of the current edge.
     */
    public Node<E1> getStart() {
        return start;
    }

    /**
     * Changes the start node of the current edge to st.
     *
     * @param st the given start node
     * @spec.modifies start
     */
    public void setStart(Node<E1> st) {
        start = st;
        checkRep();
    }

    /**
     * Returns the end node of the current edge.
     *
     * @return the end node of the current edge.
     */
    public Node<E1> getEnd() {
        return end;
    }

    /**
     * Changes the end node of the current edge to ed.
     *
     * @param ed the given end node
     * @spec.modifies end
     */
    public void setEnd(Node<E1> ed) {
        end = ed;
        checkRep();
    }

    /**
     * Returns the label of the current edge.
     *
     * @return the label of the current edge.
     */
    public E2 getLabel() {
        return label;
    }

    /**
     * Changes the label of the current edge to l.
     *
     * @param l the given label
     * @spec.modifies label
     */
    public void setLabel(E2 l) {
        label = l;
        checkRep();
    }

    /**
     * Indicates whether the given Object is equal to this.
     * The given Object is equal to this if it is an instance of Edge generics, and its start node equals
     * to the start node of this, its end node equals to the end node of this, and its label equals
     * to the label of this.
     *
     * @param other the given Object
     * @return true if Object is an instance of Edge generics, and its start node equals to the start node of
     * this, its end node equals to the end node of this, and its label equals to the label of this
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Edge<?, ?>)) {
            return false;
        }
        Edge<?, ?> e = (Edge<?, ?>) other;
        return this.label.equals(e.label) && this.start.equals(e.start) && this.end.equals(e.end);
    }

    /**
     * Returns a hash code of the object.
     * In particular, the hashCode of this is defined as this.label's hash code times 31 times 31 plus
     * this.start's hash code times 31 plus this.end's hash code.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return this.label.hashCode() * 31 * 31 + this.start.hashCode() * 31 + this.end.hashCode();
    }

    /**
     * A String representation of this, useful for testing.
     *
     * @return a String representation of this
     */
    @Override
    public String toString() {
        return " Edge: from (" + start + ") to (" + end + ") with length " + label;
    }
}
