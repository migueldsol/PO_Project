package prr.core;

import java.io.Serializable;

public class TerminalSilence extends TerminalState implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private final static String STATE_NAME = "SILENCE";

    public TerminalSilence(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public boolean changeToIdle() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalIdle(terminal));
        return true;
    }

    public boolean changeToBusy() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalBusy(terminal));
        return true;
    }

    @Override
    public boolean changeToOff() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalOff(terminal));
        return true;
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
