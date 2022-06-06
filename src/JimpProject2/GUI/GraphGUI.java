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

public class GraphGUI extends GraphGUIWrapper {
    private int nodeSize = 10;
    private int padding = (int) (nodeSize * 0.8f);
    private Graph graph;
    private Double minWeight = Double.MAX_VALUE;
    private Double maxWeight = 0.0;

    private TreeSet<Tuple> highlightedEdges = new TreeSet<>();

    private Consumer<Integer> onNodeClickListeners;

    public GraphGUI(Graph graph) {
        super();
        this.graph = graph;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < graph.getRows()* graph.getColumns(); i++)
        {
            minWeight = graph.getNode(i).getEdges().stream().map(x -> x.weight).min(Double::compareTo).orElse(Double.MAX_VALUE);
            maxWeight = graph.getNode(i).getEdges().stream().map(x -> x.weight).max(Double::compareTo).orElse(0.0);
        }
        for (int i = 0; i < graph.getRows(); i++) {
            for (int j = 0; j < graph.getColumns(); j++) {
                drawNode(g, i, j);
            }
        }
    }

    private void drawNode(Graphics g, int i, int j) {
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        ((Graphics2D) g).setColor(Color.BLACK);
        g.fillOval((nodeSize + padding) * i, (nodeSize + padding) * j, nodeSize, nodeSize);
        drawEdges(g, i, j);
    }

    private Color produceColor(double val) {
        return Color.getHSBColor((float) ((val - minWeight) / (maxWeight - minWeight)), 1, 1);
    }

    private int twoDtoOneD(int a, int b) {
        return a * graph.getColumns() + b;
    }

    private void drawEdges(Graphics g, int i, int j) {
        ArrayList<Edge> edges = graph.getNode(twoDtoOneD(i, j)).getEdges();
        for (Edge edge : edges) {
            Color newColor = highlightedEdges.contains(new Tuple(twoDtoOneD(i, j), edge.to)) ? Color.WHITE : produceColor((float) edge.weight);
            g.setColor(newColor);
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine((nodeSize + padding) * i + nodeSize / 2,
                    (nodeSize + padding) * j + nodeSize / 2,
                    (nodeSize + padding) * (edge.to / graph.getColumns()) + nodeSize / 2,
                    (nodeSize + padding) * (edge.to % graph.getColumns()) + nodeSize / 2);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (graph == null) {
            return new Dimension(0, 0);
        }
        return new Dimension((nodeSize + padding) * graph.getRows(), (nodeSize + padding) * graph.getColumns());
    }

    public void setNodeSize(int size) {
        nodeSize = size;
        padding = (int) (size * 0.8f);
    }

    public void addNodeClickListener(Consumer<Integer> listener) {
        onNodeClickListeners = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int nodeX = e.getX() / (nodeSize + padding);
        int nodeY = e.getY() / (nodeSize + padding);
        int nodeIndex = nodeX * graph.getColumns() + nodeY;
        if (nodeIndex >= graph.getColumns() * graph.getRows()) {
            return;
        }
        if (onNodeClickListeners != null) {
            onNodeClickListeners.accept(nodeIndex);
        }
    }

    public void drawHighlightedPath(int root, int destination, DijkstraResult paths) {
        if (destination == -1) {
            return;
        }
        int currentIndex = destination;
        ArrayList<Tuple> toDraw = new ArrayList<>(paths.ancestors.size());
        boolean validPath = true;
        while (currentIndex != root) {
            toDraw.add(new Tuple(currentIndex, paths.ancestors.get(currentIndex)));
            if(paths.ancestors.get(currentIndex) == -1)
            {
                validPath = false;
                break;
            }
            currentIndex = paths.ancestors.get(currentIndex);
        }
        if(validPath)
        {
            highlightedEdges.addAll(toDraw);
        }
    }
}