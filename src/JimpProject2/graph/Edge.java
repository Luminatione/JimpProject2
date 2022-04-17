package JimpProject2.graph;

public class Edge implements Comparable<Edge> {
    public int to = 0;
    public double weight = 0.0f;

    public Edge() {}
    public Edge(int to, double weight)
    {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return to + ":" + weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }
}
