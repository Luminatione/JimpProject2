package JimpProject2.graph.graphFactory;

import JimpProject2.exceptions.InvalidFileFormatException;
import JimpProject2.graph.Graph;
import JimpProject2.graph.Node;
import JimpProject2.graph.Edge;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GraphFileLoader extends GraphFactory
{

    String fileName = "";
    public GraphFileLoader() {}
    public GraphFileLoader(String fileName)
    {
        this.fileName = fileName;
    }
    @Override
    public Graph create() throws Exception
    {
        Graph graph;
        try(Scanner scanner = new Scanner(new File(fileName)))
        {
            graph = loadGraph(scanner);
        }
        return graph;
    }

    private Graph loadGraph(Scanner scanner) throws Exception {
        Graph graph;
        int rows = Integer.parseInt(scanner.next());
        int columns = Integer.parseInt(scanner.next());
        graph = new Graph(rows, columns);
        scanner.nextLine();
        for (int i = 0; i < columns * rows; i++)
        {
            parseLine(scanner, graph);
        }
        return graph;
    }

    private void parseLine(Scanner scanner, Graph graph) throws Exception {
        if(!scanner.hasNextLine())
        {
            throw new InvalidFileFormatException();
        }
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(scanner.nextLine().split("[ :\t]")));
        tokens.removeAll(Collections.singleton(""));
        ArrayList<Edge> edges = new ArrayList<>();
        for (int j = 0; j < tokens.size(); j+=2)
        {
            edges.add(new Edge(Integer.parseInt(tokens.get(j)), Double.parseDouble(tokens.get(j + 1))));
        }
        graph.addNode(new Node(edges));
    }
}
