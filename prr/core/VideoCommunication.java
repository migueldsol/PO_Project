package prr.core;

public class VideoCommunication extends InteractiveCommunication {
    public VideoCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    //SOL metodo duplicado
    public String type(){
        return "VIDEO";
    }
    public String toString(){
        return "VIDEO";
    }

    @Override
    public boolean isVideo(){
        return true;
    }
}
