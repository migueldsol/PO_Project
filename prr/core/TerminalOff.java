package prr.core;

public class TerminalOff extends TerminalState{

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
        return false;
    }

    @Override 
    public boolean changeToBusy(){
        return false;
    }

    @Override
    public boolean changeToIdle(){
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getOff());
        terminal.setState(terminal.getIdle());
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
