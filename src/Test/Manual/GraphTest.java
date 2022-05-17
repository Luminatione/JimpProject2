package Test.Manual;

import JimpProject2.algorithm.Algorithm;
import JimpProject2.algorithm.bfs.BFS;
import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.algorithm.dijkstra.DijkstraResult;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

import java.util.ArrayList;

public class GraphTest {
    public static void main(String[] args)
    {
        try
        {
            Graph graph ;//= new GraphFileLoader("test").create();
            graph = new RandomGraphGenerator(3, 5, 0.1, 3.0).create();
            ArrayList<Algorithm> toCompute = new ArrayList<>();
            toCompute.add(new Dijkstra(graph, 2));
            toCompute.add(new BFS(graph));
            System.out.println(toCompute.get(0).compute().toString());
            System.out.println(toCompute.get(1).compute().toString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
