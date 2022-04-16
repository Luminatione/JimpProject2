package JimpProject2.Graph;

public class Edge {
    int to = 0;
    double weight = 0.0f;

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
}
