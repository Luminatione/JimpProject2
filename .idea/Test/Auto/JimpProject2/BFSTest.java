package JimpProject2;

import JimpProject2.algorithm.bfs.BFS;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class BFSTest {

    void testGraphFromFile(String filename, boolean expected) throws Exception
    {
        Graph graph = new GraphFileLoader(filename).create();
        assertEquals(expected, new BFS(graph).compute());
    }
    @org.junit.jupiter.api.Test
    void compute()throws Exception {
        testGraphFromFile("graph1", true);
        testGraphFromFile("graph2", true);
        testGraphFromFile("graph3", false);
        testGraphFromFile("graph4", true);
        testGraphFromFile("graph5", true);


    }
}