package prr.core;

public class BasicTerminal extends Terminal{

    public BasicTerminal(String key, Client client, TerminalType terminalType){
        super(key, client, terminalType);
    }

    public TerminalType getTerminalType(){
        return TERMINAL_TYPE;
    }

    @Override
    public void makeCommunication(String targetKey, String type){
    }

    @Override
    public void startInteractiveCommunication(String targetKey, String type){
    }
}
