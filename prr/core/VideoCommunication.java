package prr.core;

public class VideoCommunication extends Communication {
    private int _duration;

    private boolean _state;

    public VideoCommunication(int id, Terminal origin, Terminal destination, double price) {
        super(id, origin, destination,price);
        _state = true;
    }





}

