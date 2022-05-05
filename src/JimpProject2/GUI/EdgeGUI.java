package JimpProject2.GUI;

import java.awt.*;

public class EdgeGUI extends GUIGraphElement
{
    NodeGUI origin;
    NodeGUI destination;
    public EdgeGUI(NodeGUI origin, NodeGUI destination)
    {
        super();
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(origin.getX(), origin.getY(), destination.getX(), destination.getY());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(0, 0);
    }
}
