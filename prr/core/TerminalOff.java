package prr.core;

import java.io.Serializable;

public class TerminalOff extends TerminalState implements Serializable {

    private static final long serialVersionUID = 202208091753L;

    private final static String STATE_NAME = "OFF";


    public TerminalOff(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    @Override
    public boolean changeToOff() {
        return false;
    }

    @Override
    public boolean changeToSilence() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalSilence(terminal));
        return true;
    }

    @Override 
    public boolean changeToBusy(){
        return false;
    }

    @Override
    public boolean changeToIdle(){
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalIdle(terminal));
        return true;
    }

    @Override
    public boolean canStartCommunication() {
        return false;
    }

    @Override
    public boolean canReceiveTextCommunication(){
        return false;
    }

    @Override
    public boolean canReceiveInteractiveCommunication() {
        return false;
    }

    @Override
    public boolean canEndCurrentCommunication() {
        return false;
    }

    @Override
    public boolean isOff(){
        return true;
    }
    
}
