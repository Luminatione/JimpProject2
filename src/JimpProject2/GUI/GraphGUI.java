package JimpProject2.GUI;

import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphGUI extends GUIGraphElement
{
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
        for (int i = 0; i < graph.getColumns(); i++)
        {
            for (int j = 0; j < graph.getRows(); j++)
            {
                drawNode(g, i, j);
            }
        }
    }

    private void drawNode(Graphics g, int i, int j)
    {
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        g.drawOval((nodeSize + padding) * i, (nodeSize + padding) * j, nodeSize, nodeSize);
        ArrayList<Edge> edges = graph.getNode(i * graph.getRows() + j).getEdges();
        drawEdges(g, i, j, edges);
    }

    private void drawEdges(Graphics g, int i, int j, ArrayList<Edge> edges)
    {
        for (Edge edge : edges)
        {
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine((nodeSize + padding) * i + nodeSize / 2, (nodeSize + padding) * j + nodeSize / 2,
                    (nodeSize + padding) * (edge.to / graph.getRows()) + nodeSize / 2, (nodeSize + padding) * (edge.to % graph.getRows()) + nodeSize / 2);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        if (graph == null)
        {
            return new Dimension(0, 0);
        }
        return new Dimension((nodeSize + padding) * graph.getColumns(), (nodeSize + padding) * graph.getRows());
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
        System.out.println(nodeX * graph.getRows() + nodeY);
    }
}
