package JimpProject2.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Node {
    ArrayList<Edge> edges;

    public Node(ArrayList<Edge> edges)
    {
        this.edges = edges;
    }

    public ArrayList<Edge> getEdges()
    {
        return (ArrayList<Edge>) edges.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        for(Edge edge : edges)
        {
            sb.append(edge).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
