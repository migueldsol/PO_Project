package prr.core;

import java.io.Serializable;

public class VideoCommunication extends InteractiveCommunication implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    public VideoCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    //SOL metodo duplicado
    public String type(){return "VIDEO";}

    @Override
    public boolean isVideo(){
        return true;
    }
}
