package prr.core;

public class TerminalSilence extends TerminalState{
    private final static String STATE_NAME = "SILENCE";

    public TerminalSilence(Terminal terminal){
        super(terminal, STATE_NAME);
    }

    public boolean changeToIdle() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getSilence());
        terminal.setState(terminal.getIdle());
        return true;
    }

    public boolean changeToBusy() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getSilence());
        terminal.setState(terminal.getBusy());
        return true;
    }

    @Override
    public boolean changeToOff() {
        Terminal terminal = super.getTerminal();
        terminal.changePreviousState(terminal.getSilence());
        terminal.setState(terminal.getOff());
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
