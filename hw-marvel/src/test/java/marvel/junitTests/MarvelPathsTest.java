package marvel.junitTests;

import graph.Edge;
import graph.Graph;
import graph.Node;
import marvel.MarvelPaths;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * This class contains a set of test cases that can be used to test the implementation of the MarvelPaths
 * class.
 */
public class MarvelPathsTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private final Node Ernst = new Node("Ernst-the-Bicycling-Wizard");
    private final Node Notkin = new Node("Notkin-of-the-Superhuman-Beard");
    private final Node Perkins = new Node("Perkins-the-Magical-Singing-Instructor");
    private final Node Grossman = new Node("Grossman-the-Youngest-of-them-all");
    private final Edge EN331 = new Edge(Ernst, Notkin, "CSE331");
    private final Edge EP331 = new Edge(Ernst, Perkins, "CSE331");
    private final Edge EG331 = new Edge(Ernst, Grossman, "CSE331");
    private final Edge NE331 = new Edge(Notkin, Ernst, "CSE331");
    private final Edge NP331 = new Edge(Notkin, Perkins, "CSE331");
    private final Edge NG331 = new Edge(Notkin, Grossman, "CSE331");
    private final Edge PE331 = new Edge(Perkins, Ernst, "CSE331");
    private final Edge PN331 = new Edge(Perkins, Notkin, "CSE331");
    private final Edge PG331 = new Edge(Perkins, Grossman, "CSE331");
    private final Edge GE331 = new Edge(Grossman, Ernst, "CSE331");
    private final Edge GN331 = new Edge(Grossman, Notkin, "CSE331");
    private final Edge GP331 = new Edge(Grossman, Perkins, "CSE331");
    private final Edge EN403 = new Edge(Ernst, Notkin, "CSE403");
    private final Edge NE403 = new Edge(Notkin, Ernst, "CSE403");

    private final Graph emtpyGraph = new Graph();


    private Graph makeFullGraph() {
        Graph graph = new Graph();
        graph.addNode(Ernst);
        graph.addNode(Notkin);
        graph.addNode(Perkins);
        graph.addNode(Grossman);
        graph.addEdge(EN331);
        graph.addEdge(EP331);
        graph.addEdge(EG331);
        graph.addEdge(NE331);
        graph.addEdge(NP331);
        graph.addEdge(NG331);
        graph.addEdge(PE331);
        graph.addEdge(PN331);
        graph.addEdge(PG331);
        graph.addEdge(GE331);
        graph.addEdge(GN331);
        graph.addEdge(GP331);
        graph.addEdge(EN403);
        graph.addEdge(NE403);
        return graph;
    }

    @Test
    public void testLoadGraph() {
        Graph graph = MarvelPaths.loadGraph("staffSuperheroes.csv");
        Graph graph2 = makeFullGraph();
        assertEquals(graph.getNodeByName("Ernst-the-Bicycling-Wizard").getName(), graph2.getNodeByName("Ernst-the-Bicycling-Wizard").getName());
        assertEquals(graph.getEdgeByLabel("CSE331", Ernst, "Perkins-the-Magical-Singing-Instructor").getLabel(), graph2.getEdgeByLabel("CSE331", Ernst, "Perkins-the-Magical-Singing-Instructor").getLabel());
        Graph g = MarvelPaths.loadGraph("marvel.csv");
        System.out.println(g.getAllNodes().size());
        System.out.println(g.getAllEdges().size());
    }

    @Test
    public void testBFS() {
        Graph graph = MarvelPaths.loadGraph("staffSuperheroes.csv");
        List<Edge> lst1 = MarvelPaths.BFS(graph, graph.getNodeByName("Ernst-the-Bicycling-Wizard"), graph.getNodeByName("Perkins-the-Magical-Singing-Instructor"));
        List<Edge> lst2 = new ArrayList<>();
        lst2.add(EP331);
        assertEquals(lst1.get(0).getLabel(), lst2.get(0).getLabel());
        List<Edge> lst3 = MarvelPaths.BFS(graph, graph.getNodeByName("Ernst-the-Bicycling-Wizard"), graph.getNodeByName("Notkin-of-the-Superhuman-Beard"));
        List<Edge> lst4 = new ArrayList<>();
        lst4.add(EN331);
        assertEquals(lst3.get(0).getLabel(), lst4.get(0).getLabel());
    }
}
