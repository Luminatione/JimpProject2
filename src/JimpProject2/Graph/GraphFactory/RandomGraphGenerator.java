package JimpProject2.Graph.GraphFactory;

import JimpProject2.Graph.Graph;

public class RandomGraphGenerator extends GraphFactory {

    int rows = 0;
    int columns = 0;
    public RandomGraphGenerator(){}
    public RandomGraphGenerator(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
    }
    @Override
    public Graph create() {
        return null;
    }
}
