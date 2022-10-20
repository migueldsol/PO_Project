package prr.core;

public class FancyTerminal extends Terminal{
    public final TerminalType TERMINAL_TYPE; 

    public FancyTerminal(String key, TerminalType type, Client client){
        super(key, client);
        TERMINAL_TYPE = TerminalType.FANCY;
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
