package prr.core;

public class VoiceCommunication extends Communication {
    private int _duration;

    private boolean _state;

    public VoiceCommunication(int id, Terminal origin, Terminal destination, double price) {
        super(id, origin, destination,price);
        _state = true;
    }
}
