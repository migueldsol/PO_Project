package prr.core;

import java.io.Serializable;

public abstract class TerminalState implements Serializable{

    private static final long serialVersionUID = 202208091753L;

    //BUSY, IDLE, SILENCE, OFF

    private Terminal _terminal;
    private String _name;

    public TerminalState(Terminal terminal, String name){
        _terminal = terminal;
        _name = name;
    }

    public boolean changeToOff() {
        Terminal terminal = getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalOff(terminal));
        return true;
    }
    
    public boolean changeToSilence() {
        Terminal terminal = getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalSilence(terminal));
        return true;
    }

    public boolean changeToBusy() {
        Terminal terminal = getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalBusy(terminal));
        return true;
    }

    public boolean changeToIdle() {
        Terminal terminal = getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalIdle(terminal));
        return true;
    }

    abstract public boolean canStartCommunication();
    abstract public boolean canReceiveTextCommunication();
    abstract public boolean canReceiveInteractiveCommunication();
    abstract public boolean canEndCurrentCommunication();

    public boolean isOff(){
        return false;
    }

    public boolean isIdle(){
        return false;
    }

    public boolean isBusy(){
        return false;
    }

    public boolean isSilence(){
        return false;
    }

    public Terminal getTerminal(){
        return _terminal;
    }

    public String toString(){
        return new String(_name);
    }
}
