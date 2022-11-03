package prr.core;

import java.io.Serializable;

public class FancyTerminal extends Terminal implements Serializable {

    private static final long serialVersionUID = 202208091753L;


    public FancyTerminal(String key, Client client, TerminalType terminalType){
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
