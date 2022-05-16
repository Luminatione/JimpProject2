package JimpProject2.algorithm.dijkstra;

import JimpProject2.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class DijkstraResult {
    public Graph graph = null;
    public int rootIndex = 0;
    public ArrayList<Integer> ancestors = null;
    public ArrayList<Double> pathLengths = null;

    public DijkstraResult() {}

    public DijkstraResult(Graph graph, int rootIndex)
    {
        this.graph = graph;
        this.rootIndex = rootIndex;
        ancestors = new ArrayList<>(Collections.nCopies(graph.getColumns() * graph.getRows(), -1));
        pathLengths = new ArrayList<>(Collections.nCopies(graph.getColumns() * graph.getRows(), Double.MAX_VALUE));
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "rootIndex=" + rootIndex +
                ", ancestors=" + ancestors +
                ", pathLengths=" + pathLengths +
                '}';
    }
}
