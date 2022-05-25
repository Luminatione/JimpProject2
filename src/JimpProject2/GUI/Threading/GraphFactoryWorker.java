package JimpProject2.GUI.Threading;

import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;

import javax.swing.*;
import java.util.function.Consumer;

public class GraphFactoryWorker extends SwingWorker<Void, GraphFactory>
{
    GraphFactory factory;
    Consumer<Graph> onGraphCreationComplete;
    Graph result;
    public GraphFactoryWorker(GraphFactory factory, Consumer<Graph> callback)
    {
        this.factory = factory;
        onGraphCreationComplete = callback;
    }
    @Override
    protected Void doInBackground() throws Exception {
        result = factory.create();
        return null;
    }

    @Override
    protected void done() {
        onGraphCreationComplete.accept(result);
    }
}
