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

    private final Node<String> Ernst = new Node<>("Ernst-the-Bicycling-Wizard");
    private final Node<String> Notkin = new Node<>("Notkin-of-the-Superhuman-Beard");
    private final Node<String> Perkins = new Node<>("Perkins-the-Magical-Singing-Instructor");
    private final Node<String> Grossman = new Node<>("Grossman-the-Youngest-of-them-all");
    private final Edge<String, String> EN331 = new Edge<>(Ernst, Notkin, "CSE331");
    private final Edge<String, String> EP331 = new Edge<>(Ernst, Perkins, "CSE331");
    private final Edge<String, String> EG331 = new Edge<>(Ernst, Grossman, "CSE331");
    private final Edge<String, String> NE331 = new Edge<>(Notkin, Ernst, "CSE331");
    private final Edge<String, String> NP331 = new Edge<>(Notkin, Perkins, "CSE331");
    private final Edge<String, String> NG331 = new Edge<>(Notkin, Grossman, "CSE331");
    private final Edge<String, String> PE331 = new Edge<>(Perkins, Ernst, "CSE331");
    private final Edge<String, String> PN331 = new Edge<>(Perkins, Notkin, "CSE331");
    private final Edge<String, String> PG331 = new Edge<>(Perkins, Grossman, "CSE331");
    private final Edge<String, String> GE331 = new Edge<>(Grossman, Ernst, "CSE331");
    private final Edge<String, String> GN331 = new Edge<>(Grossman, Notkin, "CSE331");
    private final Edge<String, String> GP331 = new Edge<>(Grossman, Perkins, "CSE331");
    private final Edge<String, String> EN403 = new Edge<>(Ernst, Notkin, "CSE403");
    private final Edge<String, String> NE403 = new Edge<>(Notkin, Ernst, "CSE403");

    private final Graph<String, String> emtpyGraph = new Graph<>();


    private Graph<String, String> makeFullGraph() {
        Graph<String, String> graph = new Graph<>();
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
        Graph<String, String> graph = MarvelPaths.loadGraph("staffSuperheroes.csv");
        Graph<String, String> graph2 = makeFullGraph();
        assertEquals(new Node<>("Ernst-the-Bicycling-Wizard").getValue(), new Node<>("Ernst-the-Bicycling-Wizard").getValue());
        Graph<String, String> g = MarvelPaths.loadGraph("marvel.csv");
        assertEquals(6435, g.getAllNodes().size());
        assertEquals(6417, g.getAllEdges().size());
    }

    @Test
    public void testBFS() {
        Graph<String, String> graph = MarvelPaths.loadGraph("staffSuperheroes.csv");
        List<Edge<String, String>> lst1 = MarvelPaths.BFS(graph, new Node<>("Ernst-the-Bicycling-Wizard"), new Node<>("Perkins-the-Magical-Singing-Instructor"));
        List<Edge<String, String>> lst2 = new ArrayList<>();
        lst2.add(EP331);
        assertEquals(lst1.get(0).getLabel(), lst2.get(0).getLabel());
        List<Edge<String, String>> lst3 = MarvelPaths.BFS(graph, new Node<>("Ernst-the-Bicycling-Wizard"), new Node<>("Notkin-of-the-Superhuman-Beard"));
        List<Edge<String, String>> lst4 = new ArrayList<>();
        lst4.add(EN331);
        assertEquals(lst3.get(0).getLabel(), lst4.get(0).getLabel());
    }
}
