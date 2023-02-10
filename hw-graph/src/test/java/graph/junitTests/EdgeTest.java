package graph.junitTests;

import graph.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a set of test cases that can be used to test the implementation of the Edge
 * class.
 */

public class EdgeTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private Node n0 = new Node("n0", 0);
    private Node n1 = new Node("n1");
    private Node n2 = new Node("n2");
    private Edge e00 = new Edge(n0, n0);
    private Edge e01 = new Edge(n0, n1);
    private Edge e02 = new Edge(n0, n2);
    private Edge e11 = new Edge(n1, n1);
    private Edge e12 = new Edge(n1, n2);
    private Edge e00Label = new Edge(n0, n0, "e00");
    private Edge e01Label = new Edge(n0, n1, "e01");
    private Edge e02Label = new Edge(n0, n2, "e02");
    private Edge e11Label = new Edge(n1, n1, "e11");
    private Edge e12Label = new Edge(n1, n2, "e12");

    @Test
    public void testConstructorNoLabel() {
        new Edge(n0,n0);
        new Edge(n0,n1);
        new Edge(n0,n2);
        new Edge(n1,n0);
        new Edge(n1,n1);
        new Edge(n1,n2);
    }

    @Test
    public void testConstructorWithLabel() {
        new Edge(n0,n0,"self");
        new Edge(n0,n1,"");
        new Edge(n0,n2,"test");
        new Edge(n1,n0,"label");
        new Edge(n1,n1,"e11");
        new Edge(n1,n2,"e12");
    }

    @Test
    public void testGetStart() {
        assertEquals(n0, e00.getStart());
        assertEquals(n0, e01.getStart());
        assertEquals(n0, e02.getStart());
        assertEquals(n1, e11.getStart());
        assertEquals(n1, e12.getStart());
        assertEquals(n0, e00Label.getStart());
        assertEquals(n0, e01Label.getStart());
        assertEquals(n0, e02Label.getStart());
        assertEquals(n1, e11Label.getStart());
        assertEquals(n1, e12Label.getStart());
    }

    @Test
    public void testGetEnd() {
        assertEquals(n0, e00.getEnd());
        assertEquals(n1, e01.getEnd());
        assertEquals(n2, e02.getEnd());
        assertEquals(n1, e11.getEnd());
        assertEquals(n2, e12.getEnd());
        assertEquals(n0, e00Label.getEnd());
        assertEquals(n1, e01Label.getEnd());
        assertEquals(n2, e02Label.getEnd());
        assertEquals(n1, e11Label.getEnd());
        assertEquals(n2, e12Label.getEnd());
    }

    @Test
    public void testGetLabelNoLabel() {
        assertEquals("", e00.getLabel());
        assertEquals("", e01.getLabel());
        assertEquals("", e02.getLabel());
        assertEquals("", e11.getLabel());
        assertEquals("", e12.getLabel());
    }

    @Test
    public void testGetLabelWithLabel() {
        assertEquals("e00", e00Label.getLabel());
        assertEquals("e01", e01Label.getLabel());
        assertEquals("e02", e02Label.getLabel());
        assertEquals("e11", e11Label.getLabel());
        assertEquals("e12", e12Label.getLabel());
    }

    @Test
    public void testSetStart() {
        e00.setStart(n1);
        assertEquals(n1, e00.getStart());
        e12.setStart(n2);
        assertEquals(n2, e12.getStart());
        e01Label.setStart(n2);
        assertEquals(n2, e01Label.getStart());
        e11.setStart(n0);
        assertEquals(n0, e11.getStart());
    }

    @Test
    public void testSetEnd() {
        e00.setEnd(n1);
        assertEquals(n1, e00.getEnd());
        e12.setEnd(n2);
        assertEquals(n2, e12.getEnd());
        e01Label.setEnd(n2);
        assertEquals(n2, e01Label.getEnd());
        e11.setEnd(n0);
        assertEquals(n0, e11.getEnd());
    }

    @Test
    public void testSetLabelNoLabel() {
        e00.setLabel("self");
        assertEquals("self", e00.getLabel());
        e01.setLabel("e01");
        assertEquals("e01", e01.getLabel());
        e02.setLabel("e02");
        assertEquals("e02", e02.getLabel());
        e12.setLabel("e12");
        assertEquals("e12", e12.getLabel());
    }

    @Test
    public void testSetLabelWithLabel() {
        e00Label.setLabel("  self");
        assertEquals("  self", e00Label.getLabel());
        e01Label.setLabel("  e01");
        assertEquals("  e01", e01Label.getLabel());
        e02Label.setLabel("  e02");
        assertEquals("  e02", e02Label.getLabel());
        e12Label.setLabel("  e12");
        assertEquals("  e12", e12Label.getLabel());
    }
}
