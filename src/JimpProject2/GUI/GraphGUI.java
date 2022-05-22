package JimpProject2.GUI;

import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphGUI extends GraphGUIWrapper
{
    static int rootIndex = -1, endIndex = -1;
    static int mouseClicks = 0;
    int nodeSize = 10;
    int padding = (int) (nodeSize * 0.8f);
    public static Graph graph; // shouldn't be static
    double minWeight = Double.MAX_VALUE;
    double maxWeight = 0;

    public GraphGUI(Graph graph)
    {
        super();
        this.graph = graph;//???
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
        g.drawOval((nodeSize + padding) * i, (nodeSize + padding) * j, nodeSize, nodeSize);
        System.out.println(i + " " + j + " -> " + (i * graph.getColumns() + j));
        drawEdges(g, i, j);
    }

    public Color produceColor(double val) {
        //using hsb with normalized weight as hue and excluded violet should be tested
        //this way whole color spectrum can be used and code can be simpler
        int gray = (int) Math.round((double) (val - minWeight) / (double) (maxWeight - minWeight) * 255.0);
        Color color = new Color(gray, 0, 255 - gray);
        return color;
    }

    private void drawEdges(Graphics g, int i, int j)
    {
        ArrayList<Edge> edges = graph.getNode(i * graph.getColumns() + j).getEdges();
        for (Edge edge : edges)//can be simplified using collections
        {
            if (edge.weight < minWeight)
                minWeight = edge.weight;
            if (edge.weight > maxWeight)
                maxWeight = edge.weight;
        }
        for (Edge edge : edges)
        {
            Color newColor = produceColor((float)edge.weight);
            ((Graphics2D) g).setColor(newColor);
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine((nodeSize + padding) * i + nodeSize / 2,
                    (nodeSize + padding) * j + nodeSize / 2,
                    (nodeSize + padding) * (edge.to/ (graph.getColumns())) + nodeSize / 2,
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

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int nodeX = e.getX() / (nodeSize + padding);
        int nodeY = e.getY() / (nodeSize + padding);
        System.out.println(nodeY * graph.getColumns() + nodeX);
        System.out.println(graph.getNode(nodeY * graph.getColumns() + nodeX).getEdges());
        if (mouseClicks % 2 == 0)//??
            rootIndex = nodeY * graph.getColumns() + nodeX;
        else
        {
            endIndex = nodeY * graph.getColumns() + nodeX;
        }
        mouseClicks++;
    }

    public static String beginClicked()//why is it static? It definitely shouldn't be
            //also why does it return string???
            //and usage of it doesn't make sense it should go as follows:
            //user clicks begin, then is asked to click root node
            //in next stage user can click other nodes and program should mark down path between root and clicked node
            //also path's length should be APPENDED to console
            //next click on begin is supposed to start new root selection
    {
        if(rootIndex == -1)
            return "Please select a root and destination";
        if(endIndex == -1)
            return "Please select a destination";
        else
        {
            DijkstraResult result = new Dijkstra(graph, rootIndex).compute();
            return "Length of path between " + rootIndex + " and " + endIndex + " is: " + result.pathLengths.get(endIndex);
        }
    }
}