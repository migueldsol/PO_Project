package prr.core;

public class TerminalIdle extends TerminalState{

    private final static String STATE_NAME = "IDLE";

    public TerminalIdle(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public boolean changeToIdle() {
        return false;
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
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getTerminalState());
        terminal.setState(new TerminalSilence(terminal));
        return true;
    }

    @Override
    public boolean canStartCommunication() {
        return true;
    }

    @Override
    public boolean canReceiveTextCommunication(){
        return true;
    }

    @Override
    public boolean canReceiveInteractiveCommunication() {
        return true;
    }

    @Override
    public boolean canEndCurrentCommunication() {
        return false;
    }

    @Override
    public boolean isIdle(){
        return true;
    }
    
}
