package prr.core;

public class InteractiveCommunication extends Communication{
    private int _duration;
    private boolean _ended;
    public InteractiveCommunication(int id, Terminal origin, Terminal destination){
        super(id,origin,destination);
        _ended = false;
    }

    public void setDuration(int duration){
        _duration = duration;
    }

    public int getDuration(){
        return _duration;
    }
}