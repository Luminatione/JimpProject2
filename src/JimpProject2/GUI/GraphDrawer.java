package JimpProject2.GUI;

import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GraphDrawer {
    Graph graph;
    JPanel panel;

    ArrayList<NodeGUI> nodes;
    ArrayList<EdgeGUI> edges;

    AtomicInteger nodeAvailableSpace = new AtomicInteger(0);

    public GraphDrawer(Graph graph, JPanel panel)
    {
        this.graph = graph;
        this.panel = panel;
        panel.setLayout(new GridLayout(graph.getRows(), graph.getColumns()));
        initializeNodes();
        initializeEdges();
    }

    public void bindElementsAutoResizing(JFrame window)
    {
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                onWindowResize();
            }
        });
    }

    private void initializeNodes() {
        nodes = new ArrayList<>(graph.getRows() * graph.getColumns());
        for(int i = 0; i < graph.getRows() * graph.getColumns(); i++)
        {
            NodeGUI node = new NodeGUI(graph.getNode(i), nodeAvailableSpace);
            nodes.add(node);
            panel.add(node);
        }
    }

    private void initializeEdges()
    {
        for(int i = 0; i < nodes.size(); i++)
        {
            nodes.get(i).setNeighbors(new ArrayList<>(graph.getNode(i).getEdges().stream().map(x->nodes.get(x.to)).collect(Collectors.toList())));
        }
    }

    private void onWindowResize()
    {
        nodeAvailableSpace.set(Math.min(panel.getWidth() / graph.getColumns(), panel.getHeight() / graph.getRows()));
        System.out.println(panel.getWidth() / graph.getColumns() + " " + panel.getHeight() / graph.getRows());
    }
}