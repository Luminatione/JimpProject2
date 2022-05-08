package JimpProject2.GUI;

import java.util.ArrayList;

public class DijkstraController
{
    private enum State { NOT_STARTED, WAITING_FOR_ROOT, WAITING_FOR_DESTINATION }

    private State state = State.NOT_STARTED;

    public DijkstraController()
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
