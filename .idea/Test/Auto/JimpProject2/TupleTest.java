package JimpProject2;

import JimpProject2.utility.Tuple;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    private boolean testCompareTo(int a, int b, int c, int d)
    {
        int result = new Tuple(a, b).compareTo(new Tuple(c, d));
        return result == 0;
    }
    @org.junit.jupiter.api.Test
    void testEquals() {
        assertEquals(new Tuple(0, 0), new Tuple(0, 0));
        assertEquals(new Tuple(Integer.MAX_VALUE, Integer.MAX_VALUE), new Tuple(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(new Tuple(Integer.MIN_VALUE, Integer.MIN_VALUE), new Tuple(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals(new Tuple(0, 1), new Tuple(1, 0));
        assertEquals(new Tuple(1, 0), new Tuple(0, 1));
        assertEquals(new Tuple(Integer.MAX_VALUE, Integer.MIN_VALUE), new Tuple(Integer.MIN_VALUE, Integer.MAX_VALUE));

    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        assertFalse(testCompareTo(0, 1, 0, 0));
        assertTrue(testCompareTo(0, 1, 0, 1));
        assertTrue(testCompareTo(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertTrue(testCompareTo(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE));
    }
}