package JimpProject2.algorithm;

public abstract class  Algorithm<T> implements Runnable {
    public abstract T compute();

    @Override
    public void run() {
        compute();
    }
}
