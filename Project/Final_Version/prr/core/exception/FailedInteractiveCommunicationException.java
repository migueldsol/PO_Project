package prr.core.exception;

import prr.core.TerminalState;

public class FailedInteractiveCommunicationException extends Exception{
    private TerminalState _terminalState;
    
    public FailedInteractiveCommunicationException(TerminalState terminalState){
        super();
        _terminalState = terminalState;
    }

    public TerminalState getTerminalState(){
        return _terminalState;
    }
}
