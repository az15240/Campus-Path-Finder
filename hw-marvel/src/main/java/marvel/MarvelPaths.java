package marvel;

import graph.Graph;
import graph.Edge;
import graph.Node;

import java.util.*;

import static marvel.MarvelParser.parseData;

/**
 *  <b>MarvelPaths</b> contains methods to help the graph operations to make an immutable structure of graph that
 *  contains many marvel characters. It reads from a .csv file to get some character-and-book relationships, then
 *  it sets up all characters in the file as <b>Node</b>s, then connects all characters that are in the same book
 *  with an edge labeled with the book's name. Two edges from two directions should be established. Note that it
 *  does not allow edges pointing to the character itself. Also, the graph can contain multiple edges with the same
 *  start and end nodes. All Nodes must have different names.
 *
 *  Note that this class only contains static methods and are never constructed, so it does not represent an ADT
 *  and it does not have any RI/AFs.
 */
public class MarvelPaths {

    /**
     * Constructs a new graph with information read from a .csv file.
     *
     * @param fileName the csv file to be read from.
     * @spec.requires filename is a valid file in the resources/data folder.
     * @spec.effects Constructs a new graph with information read from a .csv file.
     * @return the graph created from the .csv file
     */
    public static Graph loadGraph(String fileName) {
        Graph graph = new Graph();
        Map<String, Set<String>> data = parseData(fileName);
        for (String bookName : data.keySet()) {
            Set<String> set = data.get(bookName);
            List<String> names = new ArrayList<>(set);
            // store the characters names in a list for easier edge constructions
            for (String name : names) {
                graph.addNode(new Node(name));
            }
            for (int i = 0; i < names.size(); i++) {
                for (int j = 0; j < names.size(); j++) {
                    if (i != j) {
                        String name1 = names.get(i);
                        String name2 = names.get(j);
                        graph.addEdge(new Edge(new Node(name1), new Node(name2), bookName));
                    }
                }
            }
        }
        return graph;
    }

    /**
     * Using BFS algorithm to find a lexicographically least path from two nodes.
     * A path here is defined as a list of Edges in which the previous edge's end node is the next
     * edge's start node. In other words, they are connected to each other to form a link.
     * If two given nodes are the same one, it returns an empty list of edge.
     * If such a path is not found, it returns null.
     *
     * @param g the graph provided
     * @param st the start node in the graph
     * @param ed the end node in the graph
     * @spec.requires st != null &amp;&amp; ed != null &amp;&amp; st and ed is in g
     * @return a lexicographically least path from the two given nodes, or an empty list if the two
     *         given nodes are the same one, or null if such a path is not found.
     */
    public static List<Edge> BFS(Graph g, Node st, Node ed) {
        Queue<Node> q = new LinkedList<>();
        Map<Node, List<Edge>> m = new HashMap<>();
        q.add(st);
        m.put(st, new ArrayList<>());
        while (!q.isEmpty()) {
            Node n = q.remove();
            if (n.equals(ed)) { // destination found
                return m.get(n);
            }
            Map<String, List<String>> nextOnes = g.getChildrenFromNode(n);
            List<String> sons = new ArrayList<>(nextOnes.keySet());
            // no need to sort the children anymore thanks to the use of TreeMap in getChildrenFromNode
            for (String son : sons) { // next: find all edges from n to son. son is the string of the target
                List<String> edges = nextOnes.get(son); // edges is the List of edges that connects n to son
                Collections.sort(edges); // sort the edges on a same child to give a lexicographically least path
                for (String edgeName : edges) { // n -[edgeName]-> son
                    Node target = new Node(son);
                    if (!m.containsKey(target)) { // m is not visited yet, so add it and the path in the map
                        List<Edge> temp = new ArrayList<>(m.get(n));
                        Edge newEdge = new Edge(n, target, edgeName);
                        temp.add(newEdge);
                        m.put(target, temp);
                        q.add(target);
                    }
                }
            }
        }
        if (m.containsKey(ed)) { // ed is visited but a path not found, so st and ed are the same node
            return new ArrayList<>();
        } else { // ed is unreachable from st
            return null;
        }
    }

    /**
     * Main class to allow a user to interact with the program from the command line.
     *
     * @param args arguments from command line
     */
    public static void main(String[] args) {
        Graph g = MarvelPaths.loadGraph("marvel.csv");
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the MarvelPaths program :)\n" +
                    "You may input the two marvel superhero's names and the program will print a lexicographically " +
                    "least path connecting them.\n" +
                    "Enter \"QUIT\" to exit the program anytime.\n" +
                    "Please input the first character's name:  ");
            String name1 = scan.nextLine();
            if (name1.equals("QUIT")) break;
            if (g.getNodeByName(name1) == null) {
                System.out.println("This character is not found. Please start over.");
                continue;
            }
            System.out.println("Please input the second character's name: ");
            String name2 = scan.nextLine();
            if (name2.equals("QUIT")) break;
            if (g.getNodeByName(name2) == null) {
                System.out.println("This character is not found. Please start over.");
                continue;
            }

            List<Edge> lst = BFS(g, g.getNodeByName(name1), g.getNodeByName(name2));
            if (lst == null) {
                System.out.println("No path found!");
            } else if (lst.size() == 0) {
                System.out.println("Cannot point oneself to oneself!");
            } else {
                System.out.println("Path found from these two nodes:");
                for (Edge e : lst) {
                    System.out.println("From " + e.getStart().getName() + " to " + e.getEnd().getName() + " via " + e.getLabel());
                }
            }
            System.out.println("Ends successfully. We'll start another round.\n\n");
        }
    }
}
