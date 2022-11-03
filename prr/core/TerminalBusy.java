package prr.core;


public class TerminalBusy extends TerminalState{

    private final static String STATE_NAME = "BUSY";

    public TerminalBusy(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    @Override
    public boolean changeToIdle() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalIdle(terminal));
        return true;
    }

    @Override
    public boolean changeToBusy() {
        return false;
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
    public boolean canStartCommunication() {
        return false;
    }

    @Override
    public boolean canReceiveTextCommunication(){
        return true;
    }

    @Override
    public boolean canReceiveInteractiveCommunication() {
        return false;
    }

    @Override
    public boolean canEndCurrentCommunication() {
        return true;
    }

    @Override
    public boolean isBusy(){
        return true;
    }
    
}
