package JimpProject2.Graph.GraphFactory;

import JimpProject2.Graph.Graph;

public class GraphFileLoader extends GraphFactory {

    String fileName = "";
    public GraphFileLoader() {}
    public GraphFileLoader(String fileName)
    {
        this.fileName = fileName;
    }
    @Override
    public Graph create() {
        return null;
    }
}
