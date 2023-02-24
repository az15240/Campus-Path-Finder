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

    private Node<String> defaultNode = new Node<>("");
    private Node<String> n0 = new Node<>("0");
    private Node<String> n1 = new Node<>("1");
    private Node<String> nWeird = new Node<>("weird");
    private Node<Integer> defaultNodeWithValue = new Node<>(-1);
    private Node<Integer> n0WithValue = new Node<>(0);
    private Node<Integer> n1WithValue = new Node<>(1);
    private Node<Integer> nWeirdWithValue = new Node<>(999);


    @Test
    public void testOneArgConstructor() {
        new Node<>("");
        new Node<>("0");
        new Node<>("+1-1");
        new Node<>("randomString");
        new Node<>("\"string with quotation\"");
    }

    @Test
    public void testGetValueWithValue() {
        Integer negone = -1;
        Integer zero = 0;
        Integer one = 1;
        Integer nineninenine = 999;
        assertEquals(negone, defaultNodeWithValue.getValue());
        assertEquals(zero, n0WithValue.getValue());
        assertEquals(one, n1WithValue.getValue());
        assertEquals(nineninenine, nWeirdWithValue.getValue());
    }

    @Test
    public void testSetValue() {
        defaultNode.setValue("test");
        assertEquals("test", defaultNode.getValue());
        n0.setValue("n0");
        assertEquals("n0", n0.getValue());
        n1.setValue("999");
        assertEquals("999", n1.getValue());
        nWeird.setValue("-999");
        assertEquals("-999", nWeird.getValue());
    }
}
