package JimpProject2.graph.graphFactory;

import JimpProject2.graph.Edge;
import JimpProject2.graph.Graph;
import JimpProject2.graph.Node;

import java.util.ArrayList;
import java.util.Random;

public class RandomGraphGenerator extends GraphFactory {

    int rows = 0;
    int columns = 0;
    double minWeight = 0;
    double maxWeight = 0;

    public RandomGraphGenerator(){}

    public RandomGraphGenerator(int rows, int columns, double minWeight, double maxWeight)
    {
        this.rows = rows;
        this.columns = columns;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    @Override
    public Graph create() throws Exception
    {
        Graph graph = new Graph(rows, columns);
        for(int i = 0; i < rows * columns; i++)
        {
            graph.addNode(new Node(getNodeEdges(i)));
        }
        return graph;
    }

    private ArrayList<Edge> getNodeEdges(int nodeIndex)
    {
        Random random = new Random();
        ArrayList<Integer> neighborsIndexes = getNeighborIndexes(nodeIndex);
        ArrayList<Edge> edges = new ArrayList<>(neighborsIndexes.size());
        for(Integer neighborIndex : neighborsIndexes)
        {
            edges.add(new Edge(neighborIndex, random.nextDouble(minWeight, maxWeight)));
        }
        return edges;
    }

    private ArrayList<Integer> getNeighborIndexes(int i)
    {
        ArrayList<Integer> result = new ArrayList<>(4);
        if(i - 1 >= 0)
        {
            result.add(i - 1);
        }
        if(i / rows == (i + 1) / rows)
        {
            result.add(i + 1);
        }
        if(i - rows >= 0)
        {
            result.add(i - rows);
        }
        if(i + rows < rows * columns)
        {
            result.add(i + rows);
        }
        return result;
    }
}
