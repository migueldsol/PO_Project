package prr.core;

public class VideoCommunication extends Communication {
    private int _duration;

    private boolean _state;

    public VideoCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination, "VIDEO");
        _state = true;
    }

    public void setPrice(){

    }





}

