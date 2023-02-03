package graph;

/**
 * <b>Node</b> represents a <b>mutable</b> node in a graph. It includes a name and a
 * optional value for the node.
 */
public class Node {

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
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Constructs a new Node with given value v.
     *
     * @param n name of the node
     * @param v value of the node
     * @spec.effects Constructs a new Node with given name n and given value v.
     */
    public Node(String n, int v) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns the name of the current node.
     *
     * @return the name of the current node.
     */
    public String getName() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Changes the name of the current node to the given name n.
     *
     * @param n the given name
     * @spec.modifies name
     */
    public void setName(String n) {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Returns the value of the current node.
     *
     * @return the value of the current node.
     */
    public int getValue() {
        throw new RuntimeException("Not implemented yet!");
    }

    /**
     * Changes the value of the current node to the given value v.
     *
     * @param v the given value
     * @spec.modifies value
     */
    public void setValue(int v) {
        throw new RuntimeException("Not implemented yet!");
    }
}
