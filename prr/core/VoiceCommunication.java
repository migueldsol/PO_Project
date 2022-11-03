package prr.core;

import java.io.Serializable;

public class VoiceCommunication extends InteractiveCommunication implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    public VoiceCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    public String type(){
        return "VOICE";
    }
    @Override
    public boolean isVoice(){
        return true;
    }
}
