package graph.junitTests;

import graph.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a set of test cases that can be used to test the implementation of the Graph
 * class.
 */
public class GraphTest {
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

    // private Graph graph = new Graph();

    private Graph makeFullGraphNodes() {
        Graph graph = new Graph();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
        return graph;
    }

    private Graph makeFullGraph() {
        Graph graph = new Graph();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addEdge(e00);
        graph.addEdge(e01);
        graph.addEdge(e02);
        graph.addEdge(e11);
        graph.addEdge(e12);
        return graph;
    }

    @Test
    public void testConstructor() {
        new Graph();
    }

    @Test
    public void testAddNode() {
        Graph graph = new Graph();
        Graph graph2 = new Graph();
        graph.addNode(n0);
        graph2.addNode(n0);
        assertEquals(graph, graph2);
        graph.addNode(n1);
        graph2.addNode(n2);
        graph.addNode(n2);
        graph2.addNode(n1);
        assertEquals(graph, graph2);
    }

    @Test
    public void testRemoveNode() {
        Graph graph = makeFullGraphNodes();
        Graph graph2 = makeFullGraphNodes();
        graph.removeNode(n0);
        graph2.removeNode(n0);
        assertEquals(graph, graph2);
        graph.removeNode(n1);
        graph2.removeNode(n2);
        graph.removeNode(n2);
        graph2.removeNode(n1);
        assertEquals(graph, graph2);
    }

    @Test
    public void testAddEdge() {
        Graph graph = makeFullGraphNodes();
        Graph graph2 = makeFullGraphNodes();
        graph.addEdge(e00);
        graph2.addEdge(e00);
        assertEquals(graph, graph2);
        graph.addEdge(e01);
        graph2.addEdge(e02);
        graph.addEdge(e02);
        graph2.addEdge(e01);
        assertEquals(graph, graph2);
    }

    @Test
    public void testRemoveEdge() {
        Graph graph = makeFullGraph();
        Graph graph2 = makeFullGraph();
        graph.removeEdge(e00);
        graph2.removeEdge(e00);
        assertEquals(graph, graph2);
        graph.removeEdge(e01);
        graph2.removeEdge(e02);
        graph.removeEdge(e02);
        graph2.removeEdge(e01);
        assertEquals(graph, graph2);
    }

    @Test
    public void testGetAllNodes() {
        Graph graph = makeFullGraph();
        Set<Node> st = new HashSet<Node>();
        st.add(n0);
        st.add(n1);
        st.add(n2);
        assertEquals(st, graph.getAllNodes());
    }

    @Test
    public void testGetAllEdges() {
        Graph graph = makeFullGraph();
        Map<Node, Set<Edge>> mp = new HashMap<Node, Set<Edge>>();
        Set<Edge> st0 = new HashSet<Edge>();
        st0.add(e00);
        st0.add(e01);
        st0.add(e02);
        mp.put(n0, st0);
        Set<Edge> st1 = new HashSet<Edge>();
        st0.add(e11);
        st0.add(e12);
        mp.put(n1, st1);
        assertEquals(mp, graph.getAllNodes());
    }
}
