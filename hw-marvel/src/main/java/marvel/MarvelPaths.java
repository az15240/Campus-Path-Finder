package marvel;

import graph.Graph;
import graph.Edge;
import graph.Node;

import java.util.*;

import static marvel.MarvelParser.parseData;

/**
 *  <b>MarvelPaths</b> represents an <b>immutable</b> structure of graph that contains many marvel characters.
 *  It reads from a .csv file to get some character-and-book relationships, then it sets up all characters in
 *  the file as <b>Node</b>s, then connects all characters that are in the same book with an edge labeled with
 *  the book's name. Two edges from two directions should be established. Note that it does not allow edges
 *  pointing to the character itself. Also, the graph can contain multiple edges with the same start and end nodes.
 *  All Nodes must have different names and all Edges must have different labels.
 */
public class MarvelPaths {

    /**
     * Constructs a new, empty graph.
     *
     * @spec.effects Constructs a new, empty graph.
     */

//    /**
//     * Constructs a new graph with information read from a .csv file.
//     *
//     * @param fileName the csv file to be read from.
//     * @spec.effects Constructs a new graph with information read from a .csv file.
//     */
//    public MarvelPaths(String fileName) {
//        checkRep();
//        Map<String, Set<String>> data = parseData(fileName);
//        for (String bookName : data.keySet()) {
//            Set<String> set = data.get(bookName);
//            List<String> names = new ArrayList<>();
//            names.addAll(set);
//            for (int i = 0; i < names.size(); i++) {
//                for (int j = i + 1; j < names.size(); j++) {
//                    String name1 = names.get(i);
//                    String name2 = names.get(j);
//                    if (graph.getNodeByName(name1) == null) {
//                        graph.addNode(new Node(name1));
//                    }
//                    if (graph.getNodeByName(name2) == null) {
//                        graph.addNode(new Node(name2));
//                    }
//                    graph.addEdge(new Edge(graph.getNodeByName(name1), graph.getNodeByName(name2), bookName));
//                }
//            }
//        }
//        checkRep();
//    }

    /**
     * Constructs a new graph with information read from a .csv file.
     *
     * @param fileName the csv file to be read from.
     * @spec.effects Constructs a new graph with information read from a .csv file.
     * @spec.modifies this
     */
    public static Graph loadGraph(String fileName) {
        Graph graph = new Graph();
        Map<String, Set<String>> data = parseData(fileName);
        for (String bookName : data.keySet()) {
            Set<String> set = data.get(bookName);
            List<String> names = new ArrayList<>();
            names.addAll(set);
            for (int i = 0; i < names.size(); i++) {
                for (int j = i + 1; j < names.size(); j++) {
                    String name1 = names.get(i);
                    String name2 = names.get(j);
                    if (graph.getNodeByName(name1) == null) {
                        graph.addNode(new Node(name1));
                    }
                    if (graph.getNodeByName(name2) == null) {
                        graph.addNode(new Node(name2));
                    }
                    Node n1 = graph.getNodeByName(name1);
                    Node n2 = graph.getNodeByName(name2);
                    graph.addEdge(new Edge(n1, n2, bookName));
                    graph.addEdge(new Edge(n2, n1, bookName));
                }
            }
        }
        return graph;
    }

    public static List<Edge> BFS(Graph g, Node st, Node ed) {
        Queue<Node> q = new LinkedList<>();
        Map<Node, List<Edge>> m = new HashMap<>();
        q.add(st);
        m.put(st, new ArrayList<>());
        while (!q.isEmpty()) {
            Node n = q.remove();
            if (n.getName().equals(ed.getName())) {
                return m.get(n);
            }
            Map<String, List<String>> nextOnes = g.getChildrenFromNode(n);
            List<String> sons = new ArrayList<>(nextOnes.keySet());
            Collections.sort(sons);
            /*System.out.print("Sons display: ");
            for (String str : sons) {
                System.out.print(str + " ");
            }
            System.out.println("\n\n");*/
            for (String son : sons) {
                List<String> edges = nextOnes.get(son);
                for (String edgeName : edges) {
                    Edge e = g.getEdgeByLabel(edgeName, n.getName(), son);
                    Node target = g.getNodeByName(son);
                    if (!m.containsKey(target)) {
                        List<Edge> temp = new ArrayList<>(m.get(n));
                        temp.add(e);
                        m.put(target, temp);
                        q.add(target);
                    }
                }
            }
        }
        if (m.containsKey(ed)) {
            return new ArrayList<>();
        } else {
            return null;
        }
    }
}


