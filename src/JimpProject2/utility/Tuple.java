package JimpProject2.utility;

import java.util.Objects;

public class Tuple implements Comparable<Tuple>
{
    public Integer a;
    public Integer b;

    public Tuple(Integer a, Integer b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return (a.equals(tuple.a) && b.equals(tuple.b)) || (a.equals(tuple.b) && b.equals(tuple.a));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(a, b) + Objects.hash(b, a) + a % (b+1) + b % (a+1);
    }

    @Override
    public int compareTo(Tuple o)
    {
        return hashCode() - o.hashCode();
    }
}
