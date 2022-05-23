package JimpProject2.GUI;

import JimpProject2.graph.Graph;

import javax.swing.*;
import java.awt.*;

public class GraphDrawer {
    Graph graph = null;
    JPanel panel;
    GraphGUI graphGUI;

    public GraphDrawer(Graph graph, JPanel panel)
    {
        this.panel = panel;
        panel.setLayout(new GridLayout());
        if(graph != null)
        {
            setGraph(graph);
        }
    }

    private void initializeNodes()
    {
        if(graphGUI != null)
        {
            panel.remove(graphGUI);
        }
        graphGUI = new GraphGUI(graph);
        panel.add(graphGUI);
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
        initializeNodes();
    }

    public void setGraphGUINodeSize(int size)
    {
        if(graphGUI == null)
        {
            return;
        }
        graphGUI.setNodeSize(size);
    }

    public GraphGUI getGraphGUI()
    {
        return graphGUI;
    }
}
