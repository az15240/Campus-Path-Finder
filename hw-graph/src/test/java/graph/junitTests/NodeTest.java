package graph.junitTests;

import graph.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a set of test cases that can be used to test the implementation of the Node
 * class.
 */
public class NodeTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private Node defaultNode = new Node("");
    private Node n0 = new Node("0");
    private Node n1 = new Node("1");
    private Node nWeird = new Node("weird");
    private Node defaultNodeWithValue = new Node("", -1);
    private Node n0WithValue = new Node("0", 0);
    private Node n1WithValue = new Node("1", 1);
    private Node nWeirdWithValue = new Node("weird", 999);


    @Test
    public void testOneArgConstructor() {
        new Node("");
        new Node("0");
        new Node("+1-1");
        new Node("randomString");
        new Node("\"string with quotation\"");
    }

    @Test
    public void testTwoArgConstructor() {
        new Node("", 1);
        new Node("0", 0);
        new Node("+1-1", -1);
        new Node("randomString", 1226);
        new Node("\"string with quotation\"", 2147483647);
    }

    @Test
    public void testGetNameNoValue() {
        assertEquals("", defaultNode.getName());
        assertEquals("0", n0.getName());
        assertEquals("1", n1.getName());
        assertEquals("weird", nWeird.getName());
    }

    @Test
    public void testGetNameWithValue() {
        assertEquals("", defaultNodeWithValue.getName());
        assertEquals("0", n0WithValue.getName());
        assertEquals("1", n1WithValue.getName());
        assertEquals("weird", nWeirdWithValue.getName());
    }

    @Test
    public void testGetValueNoValue() {
        assertEquals(0, defaultNode.getValue());
        assertEquals(0, n0.getValue());
        assertEquals(0, n1.getValue());
        assertEquals(0, nWeird.getValue());
    }

    @Test
    public void testGetValueWithValue() {
        assertEquals(-1, defaultNodeWithValue.getValue());
        assertEquals(0, n0WithValue.getValue());
        assertEquals(1, n1WithValue.getValue());
        assertEquals(999, nWeirdWithValue.getValue());
    }

    @Test
    public void testSetName() {
        defaultNode.setName("name");
        assertEquals("name", defaultNode.getName());
        n0.setName("");
        assertEquals("", n0.getName());
        n1.setName("name");
        assertEquals("name", n1.getName());
        nWeird.setName("name");
        assertEquals("name", nWeird.getName());
    }

    @Test
    public void testSetValue() {
        defaultNode.setValue(-1);
        assertEquals(-1, defaultNode.getValue());
        n0.setValue(1);
        assertEquals(1, n0.getValue());
        n1.setValue(999);
        assertEquals(999, n1.getValue());
        nWeird.setValue(-999);
        assertEquals(-999, nWeird.getValue());
    }

    @Test
    public void testSetValueWithValue() {
        defaultNodeWithValue.setValue(-1);
        assertEquals(-1, defaultNodeWithValue.getValue());
        n0WithValue.setValue(1);
        assertEquals(1, n0WithValue.getValue());
        n1WithValue.setValue(999);
        assertEquals(999, n1WithValue.getValue());
        nWeirdWithValue.setValue(-999);
        assertEquals(-999, nWeirdWithValue.getValue());
    }
}
