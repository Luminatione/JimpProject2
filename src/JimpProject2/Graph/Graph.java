package JimpProject2.Graph;

import java.util.ArrayList;

public class Graph {
    int rows;
    int columns;
    ArrayList<Node> nodes;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rows).append(" ").append(columns).append("\n");
        for(Node node : nodes)
        {
            sb.append(node);
        }
        return sb.toString();
    }
}
