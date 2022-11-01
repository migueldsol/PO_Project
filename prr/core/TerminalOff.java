package prr.core;

public class TerminalOff extends TerminalState{

    private final static String STATE_NAME = "OFF";


    public TerminalOff(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public boolean changeToIdle() {
        super.getTerminal().setState(new TerminalIdle(super.getTerminal()));
        return true;
    }

    @Override
    public boolean changeToOff() {
        return false;
    }

    @Override
    public boolean changeToSilence() {
        return false;
    }

    @Override
    public boolean canStartCommunication() {
        return false;
    }

    @Override
    public boolean canReceiveComunication() {
        return false;
    }

    @Override
    public boolean canEndCurrentCommunication() {
        return false;
    }
    
}
