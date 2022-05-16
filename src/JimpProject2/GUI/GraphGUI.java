package JimpProject2.GUI;

import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;
import JimpProject2.GUI.DijkstraController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphGUI extends GUIGraphElement
{
    int rootIndex, endIndex;
    int mouseClicks = 0;
    int nodeSize = 10;
    int padding = (int) (nodeSize * 0.8f);
    public Graph graph;

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
        g.drawOval((nodeSize + padding) * i, (nodeSize + padding) * j, nodeSize, nodeSize);
        System.out.println(i + " " + j + " -> " + (i * graph.getColumns() + j));
        drawEdges(g, i, j);
    }

//    private Color[] produceColorRange(int steps)
//    {
//        float value = 1.0f; //Starting with full brightness
//        Color[] colors = new Color[steps];
//        for(int ctr = 0; ctr < steps; ctr++)
//        {
//            value = value - (1.0f/steps); //tend to darkness
//            int rgb = Color.HSBtoRGB(0.7f, 0.0f, value); //create a darker color
//            //Hue is Blue, not noticeable
//            //because Saturation is 0
//            Color color = new Color(rgb);
//            colors[ctr] = color;
//        }
//        return colors;
//    }

    private Color produceColor (float weight)
    {
        float value = 1.0f;
        value = value - (1.0f/weight);
        int rgb = Color.HSBtoRGB(0.7f, 0.0f, value);
        Color newColor = new Color(rgb);
        return newColor;
    }
//    private Color produceColor (float weight)
//    {
//
//        float rgb = 0f;
//        Color newColor = new Color (rgb, rgb, rgb);
//        return newColor;
//    }
    private void drawEdges(Graphics g, int i, int j)
    {
        ArrayList<Edge> edges = graph.getNode(i * graph.getColumns() + j).getEdges();
        int h = 0;
        for (Edge edge : edges)
        {
            //double weight = edge.weight;

        }
        for (Edge edge : edges)
        {

            Color newColor = produceColor((float)edge.weight);
            ((Graphics2D) g).setColor(newColor);
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine((nodeSize + padding) * i + nodeSize / 2, (nodeSize + padding) * j + nodeSize / 2,
                    (nodeSize + padding) * (edge.to/ (graph.getColumns())) + nodeSize / 2, (nodeSize + padding) * (edge.to % graph.getColumns()) + nodeSize / 2);
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
        if (mouseClicks % 2 == 0)
            rootIndex = nodeY * graph.getColumns() + nodeX;
        else
            endIndex = nodeY * graph.getColumns() + nodeX;
        new DijkstraController(rootIndex, endIndex, graph);
        mouseClicks++;
    }
}
