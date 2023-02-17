package graph.junitTests;

import graph.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.sql.Array;
import java.util.*;

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

    private Graph emtpyGraph = new Graph();

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
        graph.addEdge(e00Label);
        graph.addEdge(e01Label);
        graph.addEdge(e02Label);
        graph.addEdge(e11Label);
        graph.addEdge(e12Label);
        return graph;
    }

    @Test
    public void testConstructor() {
        new Graph();  // to test the constructor
    }

    @Test
    public void testAddNode() {
        Graph graph = new Graph();
        Graph graph2 = new Graph();
        graph.addNode(n0);
        graph2.addNode(n0);
        assertEquals(graph.getNodeByName("n0"), graph2.getNodeByName("n0"));
        graph.addNode(n1);
        graph2.addNode(n2);
        graph.addNode(n2);
        graph2.addNode(n1);
        assertEquals(graph.getNodeByName("n1"), graph2.getNodeByName("n1"));
    }

    @Test
    public void testAddEdge() {
        Graph graph = makeFullGraphNodes();
        Graph graph2 = makeFullGraphNodes();
        graph.addEdge(e00Label);
        graph2.addEdge(e00Label);
        assertEquals(graph.getEdgeByLabel("e00", n0, "n0"), graph2.getEdgeByLabel("e00", n0, "n0"));
        graph.addEdge(e01Label);
        graph2.addEdge(e02Label);
        graph.addEdge(e02Label);
        graph2.addEdge(e01Label);
        assertEquals(graph.getEdgeByLabel("e01", n0, "n1"), graph2.getEdgeByLabel("e01", n0, "n1"));
    }

    @Test
    public void testGetAllNodes() {
        Graph graph = makeFullGraph();
        Set<Node> st = new HashSet<Node>();
        st.add(n0);
        st.add(n1);
        st.add(n2);
        assertEquals(st, graph.getAllNodes());

        assertEquals(new HashSet<Node>(), emtpyGraph.getAllNodes());
    }

    @Test
    public void testGetAllEdges() {
        Graph graph = makeFullGraph();
        Map<Node, Set<Edge>> mp = new HashMap<Node, Set<Edge>>();
        Set<Edge> st0 = new HashSet<Edge>();
        st0.add(e00Label);
        st0.add(e01Label);
        st0.add(e02Label);
        mp.put(n0, st0);
        Set<Edge> st1 = new HashSet<Edge>();
        st0.add(e11Label);
        st0.add(e12Label);
        mp.put(n1, st1);

        assertEquals(new HashMap<>(), emtpyGraph.getAllEdges());
    }

    @Test
    public void testGetNodeByName() {
        Graph graph = makeFullGraph();
        assertEquals(null, graph.getNodeByName("does not exist!"));
        assertEquals(n0, graph.getNodeByName("n0"));
        assertEquals(n1, graph.getNodeByName("n1"));
        assertEquals(n2, graph.getNodeByName("n2"));

        assertEquals(null, emtpyGraph.getNodeByName("n0"));
    }

    @Test
    public void testGetEdgeByLabel() {
        Graph graph = makeFullGraph();
        // assertEquals(null, graph.getEdgeByLabel("does not exist!", new Node("NA"), "NA"));
        assertEquals(e00Label, graph.getEdgeByLabel("e00", n0, "n0"));
        assertEquals(e01Label, graph.getEdgeByLabel("e01", n0, "n1"));
        assertEquals(e02Label, graph.getEdgeByLabel("e02", n0, "n2"));
        assertEquals(e11Label, graph.getEdgeByLabel("e11", n1, "n1"));
        assertEquals(e12Label, graph.getEdgeByLabel("e12", n1, "n2"));

        // assertEquals(null, emtpyGraph.getEdgeByLabel("e00", n0, "n0"));
    }

    @Test
    public void testGetChildrenFromNode() {
        Graph graph = makeFullGraph();
        Map<String, List<String>> st = new HashMap<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        l1.add("e00");
        l2.add("e01");
        l3.add("e02");
        st.put("n0", l1);
        st.put("n1", l2);
        st.put("n2", l3);
        assertEquals(st, graph.getChildrenFromNode(n0));

        assertEquals(new HashMap<>(), emtpyGraph.getChildrenFromNode(n0));
    }

}
