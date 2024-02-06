package prr.core;


public class TerminalSilence extends TerminalState{

    private final static String STATE_NAME = "SILENCE";

    public TerminalSilence(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    @Override
    public boolean changeToSilence() {
        return false;
    }

    @Override
    public boolean canStartCommunication() {
        return true;
    }


    @Override
    public boolean canReceiveTextCommunication() {
        return true;
    }

    @Override
    public boolean canReceiveInteractiveCommunication(){
        return false;
    }

    @Override
    public boolean canEndCurrentCommunication() {
        return false;
    }

    @Override
    public boolean isSilence(){
        return true;
    }
    
}
