package prr.core;

import java.io.Serializable;

public abstract class ClientType implements Serializable{

    private static final long serialVersionUID = 202208091753L;

    private final String NAME;
    private final Client CLIENT;

    public ClientType(String name, Client client){
        NAME = name;
        CLIENT = client;
    }

    abstract double getTarrif(TextCommunication communication);
    abstract double getTarrif(InteractiveCommunication communication);

    abstract void changeType();
    
    public String toString(){
        return NAME;
    }

    public Client getClient(){
        return CLIENT;
    }
}
