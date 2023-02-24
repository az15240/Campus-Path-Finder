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

    private Node<String> n0 = new Node<>("n0");
    private Node<String> n1 = new Node<>("n1");
    private Node<String> n2 = new Node<>("n2");
    private Edge<String, String> e00 = new Edge<>(n0, n0);
    private Edge<String, String> e01 = new Edge<>(n0, n1);
    private Edge<String, String> e02 = new Edge<>(n0, n2);
    private Edge<String, String> e11 = new Edge<>(n1, n1);
    private Edge<String, String> e12 = new Edge<>(n1, n2);
    private Edge<String, String> e00Label = new Edge<>(n0, n0, "e00");
    private Edge<String, String> e01Label = new Edge<>(n0, n1, "e01");
    private Edge<String, String> e02Label = new Edge<>(n0, n2, "e02");
    private Edge<String, String> e11Label = new Edge<>(n1, n1, "e11");
    private Edge<String, String> e12Label = new Edge<>(n1, n2, "e12");

    private Graph<String, String> emtpyGraph = new Graph<>();

    private Graph<String, String> makeFullGraphNodes() {
        Graph<String, String> graph = new Graph<>();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.addNode(n2);
        return graph;
    }

    private Graph<String, String> makeFullGraph() {
        Graph<String, String> graph = new Graph<>();
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
        new Graph<>();  // to test the constructor
    }

    @Test
    public void testAddNode() {
        Graph<String, String> graph = new Graph<>();
        Graph<String, String> graph2 = new Graph<>();
        graph.addNode(n0);
        graph2.addNode(n0);
        assertEquals(new Node<>("n0"), new Node<>("n0"));
        graph.addNode(n1);
        graph2.addNode(n2);
        graph.addNode(n2);
        graph2.addNode(n1);
        assertEquals(new Node<>("n1"), new Node<>("n1"));
    }

    @Test
    public void testAddEdge() {
        Graph<String, String> graph = makeFullGraphNodes();
        Graph<String, String> graph2 = makeFullGraphNodes();
        graph.addEdge(e00Label);
        graph2.addEdge(e00Label);
        assertEquals(new Edge<>(new Node<>("e00"), n0, "n0"), new Edge<>(new Node<>("e00"), n0, "n0"));
        graph.addEdge(e01Label);
        graph2.addEdge(e02Label);
        graph.addEdge(e02Label);
        graph2.addEdge(e01Label);
        assertEquals(new Edge<>(new Node<>("e01"), n0, "n1"), new Edge<>(new Node<>("e01"), n0, "n1"));
    }

    @Test
    public void testGetAllNodes() {
        Graph<String, String> graph = makeFullGraph();
        Set<Node<String>> st = new HashSet<>();
        st.add(n0);
        st.add(n1);
        st.add(n2);
        assertEquals(st, graph.getAllNodes());

        assertEquals(new HashSet<Node<String>>(), emtpyGraph.getAllNodes());
    }

    @Test
    public void testGetAllEdges() {
        Map<Node<String>, Set<Edge<String, String>>> mp = new HashMap<>();
        Set<Edge<String, String>> st0 = new HashSet<>();
        st0.add(e00Label);
        st0.add(e01Label);
        st0.add(e02Label);
        mp.put(n0, st0);
        Set<Edge<String, String>> st1 = new HashSet<>();
        st0.add(e11Label);
        st0.add(e12Label);
        mp.put(n1, st1);
        assertEquals(new HashMap<>(), emtpyGraph.getAllEdges());
    }

    @Test
    public void testGetNodeByName() {
        Graph<String, String> graph = makeFullGraph();
        assertEquals(n0, new Node<>("n0"));
        assertEquals(n1, new Node<>("n1"));
        assertEquals(n2, new Node<>("n2"));
    }

    @Test
    public void testGetChildrenFromNode() {
        Graph<String, String> graph = makeFullGraph();
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
