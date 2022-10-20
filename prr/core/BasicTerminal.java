package prr.core;

public class BasicTerminal extends Terminal{
    public final TerminalType TERMINAL_TYPE; 

    public BasicTerminal(String key, TerminalType type, Client client){
        super(key, client);
        TERMINAL_TYPE = TerminalType.BASIC;
    }

    public TerminalType getTerminalType(){
        return TERMINAL_TYPE;
    }

    public void makeCommunication(String targetKey, String type){
    }

    public void startInteractiveCommunication(String targetKey, String type){
    }
}
