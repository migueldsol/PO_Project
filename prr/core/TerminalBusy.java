package prr.core;

public class TerminalBusy extends TerminalState{
    private final static String STATE_NAME = "BUSY";

    public TerminalBusy(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public boolean changeToIdle() {
        return false;
    }

    public boolean changeToBusy() {
        return false;
    }

    @Override
    public boolean canStartCommunication() {
        return false;
    }

    @Override
    public boolean canReceiveComunication() {
        //FIXME Se for de texto pode receber
        return false;
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
    public boolean canEndCurrentCommunication() {
        return true;
    }
    
}
