package JimpProject2;

import JimpProject2.graph.Graph;
import JimpProject2.graph.Node;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @org.junit.jupiter.api.Test
    void getNode() throws Exception {
        Graph graph = new GraphFileLoader("graph1").create();
        assertDoesNotThrow(() -> graph.getNode(0));
        assertDoesNotThrow(() -> graph.getNode(5));
        assertThrows(IndexOutOfBoundsException.class, () -> graph.getNode(30));
    }

    @org.junit.jupiter.api.Test
    void addNode() {
        Graph graph = new Graph(1, 2);
        assertDoesNotThrow(() -> graph.addNode(new Node(new ArrayList<>())));
        assertDoesNotThrow(() -> graph.addNode(new Node(new ArrayList<>())));
        assertThrows(Exception.class, () -> graph.addNode(new Node(new ArrayList<>())));
    }

    @org.junit.jupiter.api.Test
    void getRows() {
        int a = 10, b = 5;
        Graph graph = new Graph(a, b);
        assertEquals(a, graph.getRows());
        a = 0;
        graph = new Graph(a, b);
        assertEquals(a, graph.getRows());
    }
    @org.junit.jupiter.api.Test
    void getColumns() {
        int a = 10, b = 5;
        Graph graph = new Graph(a, b);
        assertEquals(b, graph.getColumns());
        b = 0;
        graph = new Graph(a, b);
        assertEquals(b, graph.getColumns());
    }

    @org.junit.jupiter.api.Test
    void testToString() throws Exception {
        Graph graph = new GraphFileLoader("graph1").create();
        assertEquals("2 3\n" +
                "\t1:1.2702731182070195 3:1.1409909120589492 \n" +
                "\t0:1.5304732116928366 2:1.590803568661677 4:1.635162302355842 \n" +
                "\t1:1.5431340761505798 5:1.1537120464481232 \n" +
                "\t4:1.174160305810364 0:1.0626475699140745 \n" +
                "\t3:1.829144881126159 5:1.586744631457607 1:1.4464825576191331 \n" +
                "\t4:1.5669181800046017 2:1.1486626193986598 \n",
                graph.toString());
        graph = new GraphFileLoader("graph2").create();
        assertEquals("4 4\n" +
                "\t1:1.1914170463273002 4:1.4060337453106018 \n" +
                "\t0:1.8219952589659139 2:1.6413664641393424 5:1.9218567036508798 \n" +
                "\t1:1.1724294361491228 3:1.11269084052774 6:1.5134401644717537 \n" +
                "\t2:1.9324211769393749 7:1.9024905765254445 \n" +
                "\t5:1.5761868104780632 0:1.83620768885056 8:1.162211121341178 \n" +
                "\t4:1.491493076726719 6:1.6522684911005516 1:1.5664398971963511 9:1.118602031535712 \n" +
                "\t5:1.5690098135283082 7:1.3588267311077984 2:1.6717103576486438 10:1.552608948632602 \n" +
                "\t6:1.1419289719977552 3:1.5312966568819018 11:1.859668215532571 \n" +
                "\t9:1.2399748209853931 4:1.9888927111586847 12:1.9644242475770415 \n" +
                "\t8:1.521432529166004 10:1.4849049587272942 5:1.6954843847091787 13:1.7481680655616336 \n" +
                "\t9:1.1688389107580779 11:1.5619803234562533 6:1.46401420457922 14:1.6387619393712312 \n" +
                "\t10:1.4422481898194321 7:1.962065270151319 15:1.5284092010909285 \n" +
                "\t13:1.33061745464142 8:1.1223498224273616 \n" +
                "\t12:1.1422344750722115 14:1.9476135880017011 9:1.0306919859226986 \n" +
                "\t13:1.867006716070916 15:1.8771046473813355 10:1.6388624900950841 \n" +
                "\t14:1.183086416455231 11:1.631001441525148 \n",
                graph.toString());

    }
}