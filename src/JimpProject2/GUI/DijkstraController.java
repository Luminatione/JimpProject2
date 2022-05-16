package JimpProject2.GUI;

import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import java.util.ArrayList;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

public class DijkstraController
{
    private enum State { NOT_STARTED, WAITING_FOR_ROOT, WAITING_FOR_DESTINATION }

    private State state = State.NOT_STARTED;

    public DijkstraController(int root, int destination, Graph graph)
    {
        DijkstraResult result = new Dijkstra(graph, root).compute();
        System.out.println("The root is: " + root);
    }

    public void onNodeClick()
    {
        if(state == State.NOT_STARTED)
        {
            state = State.WAITING_FOR_ROOT;
        }
        else if(state == State.WAITING_FOR_ROOT)
        {

        }
        else if(state == State.WAITING_FOR_DESTINATION)
        {

        }
    }
}
