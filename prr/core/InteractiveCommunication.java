package prr.core;

public abstract class InteractiveCommunication extends Communication{
    private int _duration;
    private boolean _ended;
    public InteractiveCommunication(int id, Terminal origin, Terminal destination){
        super(id,origin,destination);
        _ended = false;
    }

    abstract public String type();

    public String toString(){
        return type() + "|" + getId() + "|" + getOriginId() + "|" +
                getDestinationId() + "|" + _duration + "|" + Math.round(getPrice()) + "|" + getState();
    }
    public void setDuration(int duration){
        _duration = duration;
    }

    public int getDuration(){
        return _duration;
    }
}
