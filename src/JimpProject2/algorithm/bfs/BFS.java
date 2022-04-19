package JimpProject2.algorithm.bfs;

import JimpProject2.algorithm.Algorithm;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

import java.util.*;

public class BFS extends Algorithm<Boolean> {
    Graph graph;

    public BFS(Graph graph)
    {
        this.graph = graph;
    }

    @Override
    public Boolean compute() {
        ArrayList<Boolean> visitsState = new ArrayList<>(Collections.nCopies(graph.getColumns() * graph.getRows(), false));
        ArrayList<Edge> firstNodeEdges = graph.getNode(0).getEdges();
        int visited = 1;
        visitsState.set(0, true);
        Queue<Edge> toVisit = new LinkedList<>(firstNodeEdges);
        while (!toVisit.isEmpty())
        {
            int currentNodeIndex = toVisit.poll().to;
            if(!visitsState.get(currentNodeIndex))
            {
                visited++;
                toVisit.addAll(graph.getNode(currentNodeIndex).getEdges());
                visitsState.set(currentNodeIndex, true);
            }
        }
        return visited == graph.getColumns() * graph.getRows();
    }
}
