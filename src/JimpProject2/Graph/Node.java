package JimpProject2.Graph;

import java.util.ArrayList;

public class Node {
    ArrayList<Edge> edges;

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
