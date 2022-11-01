package prr.core;

public class TerminalSilence extends TerminalState{
    private final static String STATE_NAME = "SILENCE";

    public TerminalSilence(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public void changeToIdle() {
        Terminal terminal = super.getTerminal();
        terminal.setState(terminal.getIdle());
    }

    public void changeToBusy() {
        Terminal terminal = super.getTerminal();
        terminal.setState(terminal.getBusy());
    }

    public boolean changeToOff() {
        Terminal terminal = super.getTerminal();
        terminal.setState(terminal.getOff());
        return true;
    }

    public boolean changeToSilence() {
        return false;
    }

    @Override
    public boolean canStartCommunication() {
        return false;
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
    
}
