package JimpProject2.GUI;
import JimpProject2.GUI.GraphGUI;

import JimpProject2.GUI.Threading.AlgorithmWorker;
import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import java.util.ArrayList;
import JimpProject2.graph.Graph;

import javax.swing.*;


public class DijkstraController
{
    private enum State { NOT_STARTED, WAITING_FOR_ROOT, WAITING_FOR_DESTINATION }

    private State state = State.NOT_STARTED;

    Graph graph;
    int root, destination;
    Dijkstra dijkstra;
    DijkstraResult result;
    GraphGUI graphGUI;
    Runnable repaint;
    AlgorithmWorker<DijkstraResult> worker;
    public DijkstraController(Graph graph, GraphGUI graphGUI, Runnable repaint)
    {
        this.graph = graph;
        this.graphGUI = graphGUI;
        this.graphGUI.addNodeClickListener(this::onNodeClick);
        this.repaint = repaint;
    }

    public void start()
    {
        state = State.WAITING_FOR_ROOT;
    }
    private void onDijkstraComplete(DijkstraResult result)
    {
        this.result = result;
    }
    public void onNodeClick(int nodeIndex)
    {
        if(state == State.WAITING_FOR_ROOT)
        {
            root = nodeIndex;
            worker = new AlgorithmWorker<>(new Dijkstra(graph, root), this::onDijkstraComplete);
            worker.execute();
            state = State.WAITING_FOR_DESTINATION;
        }
        else if(state ==  State.WAITING_FOR_DESTINATION && worker.isDone())
        {
            destination = nodeIndex;
            graphGUI.drawHighlightedPath(root, destination, result);
            repaint.run();
        }
    }
}
