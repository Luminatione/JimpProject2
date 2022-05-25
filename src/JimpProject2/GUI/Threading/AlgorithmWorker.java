package JimpProject2.GUI.Threading;

import JimpProject2.algorithm.Algorithm;

import javax.swing.*;
import java.util.function.Consumer;

public class AlgorithmWorker <T> extends SwingWorker<Void, Algorithm<T>>
{
    private Algorithm<T> algorithm;
    private Consumer<T> onAlgorithmEnd;
    T result;
    public AlgorithmWorker(Algorithm<T> algorithm, Consumer<T> callback)
    {
        this.algorithm = algorithm;
        onAlgorithmEnd = callback;
    }
    @Override
    protected Void doInBackground()
    {
        result = algorithm.compute();
        return null;
    }

    @Override
    protected void done()
    {
        onAlgorithmEnd.accept(result);
    }
}
