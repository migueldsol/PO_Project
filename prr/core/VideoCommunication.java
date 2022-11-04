package prr.core;


public class VideoCommunication extends InteractiveCommunication{

    public VideoCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    public String type(){return "VIDEO";}

    @Override
    public boolean isVideo(){
        return true;
    }
}
