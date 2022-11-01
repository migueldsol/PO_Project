package prr.core;

public class VideoCommunication extends Communication {
    private int _duration;


    public VideoCommunication(int id, Terminal origin, Terminal destination, int duration) {
        super(id, origin, destination);
        _duration = duration;
    }





}

