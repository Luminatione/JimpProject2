package Test.Manual;

import JimpProject2.utility.Tuple;

import java.util.ArrayList;

public class HashManualTest {

    public static void main(String[] args)
    {
        int maxTest = 1000;
        ArrayList<Integer> hashes = new ArrayList<>();
        for(int i = 0; i < maxTest; ++i)
        {
            for(int j = 0; j < maxTest; ++j)
            {
                hashes.add(new Tuple(i, j).hashCode());
            }
        }
        ArrayList<Integer> occurances = new ArrayList<>(70000);
        for(int i = 0; i < 70000; ++i)
        {
            occurances.add(0);
        }
        for(Integer i : hashes)
        {
            occurances.set(i, occurances.get(i) + 1);
        }
        int collisions = 0;
        for(int i = 0; i < occurances.size(); ++i)
        {
            if(occurances.get(i) > 2)
            {
                collisions++;
            }
        }
        System.out.println(collisions);
    }
}
