package JimpProject2.graph.graphFactory;

import JimpProject2.exceptions.InvalidFileFormatException;
import JimpProject2.graph.Graph;
import JimpProject2.graph.Node;
import JimpProject2.graph.Edge;

import java.io.*;
import java.util.ArrayList;
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
             int rows = readIntFromScanner(scanner);
             int columns = readIntFromScanner(scanner);
             graph = new Graph(rows, columns);
             ArrayList<Edge> edges = null;
             for(int i = 0; i < rows * columns; i++)
             {
                 edges = new ArrayList<>();
                 int to = readIntFromScanner(scanner);
                 double weight = readDoubleFromScanner(scanner);
                 edges.add(new Edge(to, weight));
             }
             graph.addNode(new Node(edges));
        }
        return graph;
    }
    private int readIntFromScanner(Scanner scanner) throws InvalidFileFormatException
    {
        if(scanner.hasNextInt())
        {
            throw new InvalidFileFormatException();
        }
        return scanner.nextInt();
    }
    private double readDoubleFromScanner(Scanner scanner) throws InvalidFileFormatException
    {
        if(scanner.hasNextDouble())
        {
            throw new InvalidFileFormatException();
        }
        return scanner.nextDouble();
    }
}
