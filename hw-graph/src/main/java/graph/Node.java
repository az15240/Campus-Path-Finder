package graph;

/**
 * <b>Node</b> represents a <b>mutable</b> node in a graph. It includes a value of generic type.
 * Two <b>Node</b>s are equal if they have the same values.
 * @param <E> the generic type
 */
public class Node<E> {

    // Abstraction Function:
    // Node n, represents a node in a graph.
    // In a Node, value represents the value of the node.
    //
    // Representation Invariant:
    // this != null

    /**
     * Value of the node, in generic type.
     */
    private E value;

    /**
     * Constructs a Node object with given generic type value v.
     * @param v given generic type value v
     */
    public Node(E v) {
        value = v;
    }

    // Throws an exception if the representation invariant is violated.
    private void checkRep() {
        assert (this != null);
    }

    /**
     * Returns the value of the current node.
     *
     * @return the value of the current node.
     */
    public E getValue() {
        return value;
    }

    /**
     * Changes the value of the current node to the given value v.
     *
     * @param v the given value
     * @spec.modifies value
     */
    public void setValue(E v) {
        value = v;
        checkRep();
    }

    /**
     * Indicates whether the given Object is equal to this.
     * The given Object is equal to this if it is an instance of Node generics, and its value equals
     * to the value of this.
     *
     * @param other the given Object
     * @return true if Object is an instance of Node generics, and its value equals to the value of this
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Node<?>)) {
            return false;
        }
        Node<?> n = (Node<?>) other;
        return this.value.equals(n.value);
    }

    /**
     * Returns a hash code of the object.
     * In particular, the hashCode of this is defined as this.value's hash code.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * A String representation of this, useful for testing.
     *
     * @return a String representation of this
     */
    @Override
    public String toString() {
        return " Node value: " + value + " ";
    }
}