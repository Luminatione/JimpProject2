package JimpProject2.Graph;

import java.util.ArrayList;

public class Graph {
    int rows = 0;
    int columns = 0;
    ArrayList<Node> nodes = new ArrayList<>();

    public Graph(){}

    public Graph (int rows, int columns)
    {
        this.columns = columns;
        this.rows = rows;
        nodes.ensureCapacity(rows * columns);
    }

    public Node getNode(int i)
    {
        return nodes.get(i);
    }
    public void addNode(Node node) throws Exception
    {
        if(nodes.size() >= rows * columns)
        {
            throw new Exception("Can not add edge to full graph");
        }
        nodes.add(node);
    }

    public int getRows()
    {
        return rows;
    }
    public int getColumns()
    {
        return columns;
    }

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
