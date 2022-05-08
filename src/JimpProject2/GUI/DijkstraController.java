package JimpProject2.GUI;

import java.util.ArrayList;

public class DijkstraController
{
    private enum State { NOT_STARTED, WAITING_FOR_ROOT, WAITING_FOR_DESTINATION }

    private State state = State.NOT_STARTED;
    private ArrayList<NodeGUI> nodes;
    private NodeGUI root;

    public DijkstraController(ArrayList<NodeGUI> nodes)
    {

    }

    public void onNodeClick()
    {
        if(state == State.NOT_STARTED)
        {
            state = State.WAITING_FOR_ROOT;
        }
        else if(state == State.WAITING_FOR_ROOT)
        {

        }
        else if(state == State.WAITING_FOR_DESTINATION)
        {

        }
    }
}
