package Test.Manual;

import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFileLoader;

public class GraphTest {
    public static void main(String[] args)
    {
        try {
            System.out.println(System.getProperty("user.dir"));
            Graph graph = new GraphFileLoader("test").create();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
