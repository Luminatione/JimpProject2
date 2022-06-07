package JimpProject2.algorithm.dijkstra;

import JimpProject2.algorithm.Algorithm;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

import java.util.*;

public class Dijkstra extends Algorithm<DijkstraResult> {
    Graph graph;
    int rootIndex;

    ArrayList<Boolean> visitsStates = null;
    Queue<Edge> toVisit = null;
    DijkstraResult result = null;

    public Dijkstra(Graph graph, int rootIndex)
    {
        this.graph = graph;
        this.rootIndex = rootIndex;
    }

    @Override
    public DijkstraResult compute()
    {
        result = new DijkstraResult(graph, rootIndex);
        toVisit = new PriorityQueue<>(graph.getColumns() + graph.getRows(), Comparator.comparingDouble((Edge a) -> a.weight));
        visitsStates = new ArrayList<>(Collections.nCopies(graph.getColumns() * graph.getRows(), false));
        toVisit.add(new Edge(rootIndex, 0));
        visitsStates.set(rootIndex, true);
        result.ancestors.set(rootIndex, rootIndex);
        result.pathLengths.set(rootIndex, 0.0);
        while(!toVisit.isEmpty())
        {
            computeNode();
        }
        return result;
    }

    private void computeNode() throws NullPointerException
    {

        if(toVisit.peek() == null)
        {
            throw new NullPointerException();
        }
        int currentIndex = toVisit.poll().to;
        ArrayList<Edge> edges = graph.getNode(currentIndex).getEdges();
        for(Edge edge : edges)
        {
            if(!visitsStates.get(edge.to))
            {
                toVisit.add(edge);
                visitsStates.set(edge.to, true);
            }
            double pathLengthToNeighbor = result.pathLengths.get(currentIndex) + edge.weight;
            if(result.pathLengths.get(edge.to) > pathLengthToNeighbor)
            {
                result.ancestors.set(edge.to, currentIndex);
                result.pathLengths.set(edge.to, pathLengthToNeighbor);
            }
        }
    }
}
