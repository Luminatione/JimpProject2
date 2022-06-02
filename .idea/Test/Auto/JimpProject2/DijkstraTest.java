package JimpProject2;

import JimpProject2.algorithm.bfs.BFS;
import JimpProject2.algorithm.dijkstra.Dijkstra;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    void testGraphFromFile(String filename, String expected, int root) throws Exception
    {
        Graph graph = new GraphFileLoader(filename).create();
        assertEquals(expected, new Dijkstra(graph, root).compute().toString());
    }
    @org.junit.jupiter.api.Test
    void compute()
    {
        try
        {
            testGraphFromFile("graph1",
                    "rootIndex=2, ancestors=[1, 2, 2, 0, 5, 2]," +
                            " pathLengths=[3.0736072878434166, 1.5431340761505798, 0.0, 4.214598199902365," +
                            " 2.720630226452725, 1.1537120464481232]}",
                    2);
            testGraphFromFile("graph2",
                    "rootIndex=8, ancestors=[4, 5, 6, 2, 8, 9, 10, 6, 8, 8, 9, 10, 8, 9, 10, 11]," +
                            " pathLengths=[3.825100400009245, 4.501899102890923, 5.860604341940551," +
                            " 6.973295182468291, 1.9888927111586847, 2.935459205694572, 4.188893984291907," +
                            " 5.547720715399706, 0.0, 1.2399748209853931, 2.7248797797126874, 4.286860103168941," +
                            " 1.9644242475770415, 2.9881428865470268, 4.363641719083919, 5.8152693042598695]}",
                    8);
            testGraphFromFile("graph3",
                    "rootIndex=3, ancestors=[3, 4, 1, 3, 3, 4, 3, 4, -1]," +
                            " pathLengths=[1.5371715477895034, 2.435290560611981, 3.9963916441251297," +
                            " 0.0, 1.3404324546932758, 2.6439444634445626, 1.4519771194959725," +
                            " 3.2234312283776707, 1.7976931348623157E308]}",
                    3);
            testGraphFromFile("graph5",
                    "rootIndex=0, ancestors=[0], pathLengths=[0.0]}",
                    0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}