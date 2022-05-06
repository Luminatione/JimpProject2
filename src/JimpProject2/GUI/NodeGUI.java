package JimpProject2.GUI;

import JimpProject2.graph.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class NodeGUI extends GUIGraphElement
{
    int minSize = 4;
    int maxSize = 30;
    AtomicInteger preferredSize;

    ArrayList<NodeGUI> neighbors = new ArrayList<>();
    public Node node;
    public NodeGUI(Node node, AtomicInteger preferredSize)
    {
        super();
        this.preferredSize = preferredSize;
        this.node = node;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(0, 0, getPreferredSize().width, getPreferredSize().height);
        for (NodeGUI neighbor : neighbors) {
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine(getPreferredSize().width / 2, getPreferredSize().height / 2,
                    neighbor.getX() - getX() + getPreferredSize().width / 2, neighbor.getY() - getY() + getPreferredSize().height / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if(preferredSize.get() > maxSize)
        {
            return getMaximumSize()
;        }
        else if(preferredSize.get() < minSize)
        {
            return getMinimumSize();
        }
        return new Dimension(preferredSize.get(), preferredSize.get());
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(minSize, minSize);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(maxSize, maxSize);
    }

    public void setNeighbors(ArrayList<NodeGUI> neighbors)
    {
        this.neighbors = neighbors;
    }
}
