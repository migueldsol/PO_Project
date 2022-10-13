package prr.core;

public class BasicTerminal extends Terminal{
    
    public BasicTerminal(String key, String type, Client client){
        super(key, type, client);
    }

    public void makeCommunication(String targetKey, String type){
    }

    public void startInteractiveCommunication(String targetKey, String type){
    }
}
