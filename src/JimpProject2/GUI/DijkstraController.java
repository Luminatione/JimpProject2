package JimpProject2.GUI;

import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import java.util.ArrayList;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

public class DijkstraController
{
    Graph graph;
    int root, destination;
    public DijkstraController(int root, int destination, Graph graph)
    {
        this.graph = graph;
        this.root = root;
        this.destination = destination;
        DijkstraResult result = new Dijkstra(graph, root).compute();
        System.out.println("The root is: " + result.toString());
    }
}
