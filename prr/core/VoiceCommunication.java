package prr.core;


public class VoiceCommunication extends InteractiveCommunication{

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
