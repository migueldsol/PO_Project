package prr.core;

public class VoiceCommunication extends Communication {
    private int _duration;

    private boolean _state;

    public VoiceCommunication(int id, Terminal origin, Terminal destination, int duration) {
        super(id, origin, destination);
        _duration = duration;
        _state = true;
    }
}
