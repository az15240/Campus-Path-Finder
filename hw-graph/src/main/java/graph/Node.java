package graph;

/**
 * <b>Node</b> represents a <b>mutable</b> node in a graph. It includes a name and a
 * optional value for the node. Two <b>Node</b>s are equal if they have the same name.
 */
public class Node<E> {

    // Abstraction Function:
    // Node n, represents a node in a graph.
    // In a Node, name represents the name of the node,
    // and value represents the value of the node.
    //
    // Representation Invariant:
    // this != null

    /**
     * Value of the node, in integer
     */
    private E value;

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
     * The given Object is equal to this if it is an instance of Node, and its name equals
     * to the name of this.
     *
     * @param other the given Object
     * @return true if Object is an instance of Node, and its name equals to the name of this
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
     * In particular, the hashCode of this is defined as this.name's hash code.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return " Node value: " + value + " ";
    }
}