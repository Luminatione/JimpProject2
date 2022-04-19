package Test.Manual;

import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

public class GraphTest {
    public static void main(String[] args)
    {
        try
        {
            Graph graph = new GraphFileLoader("test").create();
            graph = new RandomGraphGenerator(3, 5, 0.1, 3.0).create();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
