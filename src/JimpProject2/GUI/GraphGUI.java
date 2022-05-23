package JimpProject2.GUI;

import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;
import JimpProject2.utility.Tuple;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class GraphGUI extends GraphGUIWrapper
{
    private int nodeSize = 10;
    private int padding = (int) (nodeSize * 0.8f);
    private Graph graph;
    private double minWeight = Double.MAX_VALUE;
    private double maxWeight = 0;

    private TreeSet <Tuple> highlightedEdges = new TreeSet<>();

    private Consumer<Integer> onNodeClickListeners;

    public GraphGUI(Graph graph)
    {
        super();
        this.graph = graph;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0; i < graph.getRows(); i++)
        {
            for (int j = 0; j < graph.getColumns(); j++)
            {
                drawNode(g, i, j);
            }
        }
    }

    private void drawNode(Graphics g, int i, int j)
    {
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        ((Graphics2D) g).setColor(Color.BLACK);
        g.drawOval((nodeSize + padding) * i, (nodeSize + padding) * j, nodeSize, nodeSize);
        drawEdges(g, i, j);
    }

    private Color produceColor(double val) {
        //using hsb with normalized weight as hue and excluded violet should be tested
        //this way whole color spectrum can be used and code can be simpler
        int gray = (int) Math.round((double) (val - minWeight) / (double) (maxWeight - minWeight) * 255.0);
        Color color = new Color(gray, 0, 255 - gray);
        return color;
    }

    private int twoDtoOneD(int a, int b)
    {
        return a * graph.getColumns() + b;
    }

    private void drawEdges(Graphics g, int i, int j)
    {
        ArrayList<Edge> edges = graph.getNode(twoDtoOneD(i, j)).getEdges();
        for (Edge edge : edges)//can be simplified using collections
        {
            if (edge.weight < minWeight)
                minWeight = edge.weight;
            if (edge.weight > maxWeight)
                maxWeight = edge.weight;
        }
        for (Edge edge : edges)
        {
            Color newColor = highlightedEdges.contains(new Tuple(twoDtoOneD(i, j), edge.to)) ?  Color.WHITE : produceColor((float)edge.weight);
            g.setColor(newColor);
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine((nodeSize + padding) * i + nodeSize / 2,
                    (nodeSize + padding) * j + nodeSize / 2,
                    (nodeSize + padding) * (edge.to / graph.getColumns()) + nodeSize / 2,
                    (nodeSize + padding) * (edge.to % graph.getColumns()) + nodeSize / 2);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        if (graph == null)
        {
            return new Dimension(0, 0);
        }
        return new Dimension((nodeSize + padding) * graph.getRows(), (nodeSize + padding) * graph.getColumns());
    }

    public void setNodeSize(int size)
    {
        nodeSize = size;
        padding = (int) (size * 0.8f);
    }

    public void addNodeClickListener(Consumer<Integer> listener)
    {
         onNodeClickListeners = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int nodeX = e.getX() / (nodeSize + padding);
        int nodeY = e.getY() / (nodeSize + padding);
        int nodeIndex = nodeX * graph.getColumns() + nodeY;
        if(nodeIndex >= graph.getColumns() * graph.getRows())
        {
            return;
        }
        if(onNodeClickListeners != null)
        {
            onNodeClickListeners.accept(nodeIndex);
        }
    }
    public void drawHighlightedPath(int root, int destination, DijkstraResult paths)
    {
        if(destination == -1)
        {
            return;
        }
        int currentIndex = destination;
        while(currentIndex != root)
        {
            highlightedEdges.add(new Tuple(currentIndex, paths.ancestors.get(currentIndex)));
            currentIndex = paths.ancestors.get(currentIndex);
        }
    }
}