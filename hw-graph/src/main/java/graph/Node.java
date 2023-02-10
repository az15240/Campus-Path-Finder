package graph;

/**
 * <b>Node</b> represents a <b>mutable</b> node in a graph. It includes a name and a
 * optional value for the node.
 */
public class Node {

    // Abstraction Function:
    // Node n, represents a node in a graph.
    // In a Node, name represents the name of the node,
    // and value represents the value of the node.
    //
    // Representation Invariant:
    // this != null

    /**
     * Name of the node
     */
    private String name;

    /**
     * Value of the node, in integer
     */
    private int value;

    /**
     * Constructs a new Node with default value 0.
     *
     * @param n name of the node
     * @spec.effects Constructs a new Node with given name n and default value 0.
     */
    public Node(String n) {
        name = n;
        value = Integer.MIN_VALUE;
    }

    /**
     * Constructs a new Node with given value v.
     *
     * @param n name of the node
     * @param v value of the node
     * @spec.effects Constructs a new Node with given name n and given value v.
     */
    public Node(String n, int v) {
        name = n;
        value = v;
    }

    /**
     * Throws an exception if the representation invariant is violated.
     */
    private void checkRep() {
        assert (this != null);
    }

    /**
     * Returns the name of the current node.
     *
     * @return the name of the current node.
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the current node to the given name n.
     *
     * @param n the given name
     * @spec.modifies name
     */
    public void setName(String n) {
        name = n;
        checkRep();
    }

    /**
     * Returns the value of the current node.
     *
     * @return the value of the current node.
     */
    public int getValue() {
        return value;
    }

    /**
     * Changes the value of the current node to the given value v.
     *
     * @param v the given value
     * @spec.modifies value
     */
    public void setValue(int v) {
        value = v;
        checkRep();
    }
}
