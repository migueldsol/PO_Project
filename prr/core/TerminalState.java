package prr.core;

import java.io.Serializable;

public abstract class TerminalState implements Serializable{

    private static final long serialVersionUID = 202208091753L;

    //BUSY, IDLE, SILENCE, OFF

    //FIXME meter coisas final
    private Terminal _terminal;
    private String _name;

    public TerminalState(Terminal terminal, String name){
        _terminal = terminal;
        _name = name;
    }

    public abstract boolean changeToOff();
    public abstract boolean changeToSilence();

    abstract public boolean canStartCommunication();
    abstract public boolean canReceiveTextCommunication();
    abstract public boolean canReceiveInteractiveCommunication();
    abstract public boolean canEndCurrentCommunication();

    //FIXME pode estar errado
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
