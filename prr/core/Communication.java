package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private final int ID;
    private Terminal _origin;
    private Terminal _destination;

    public Communication(int id,Terminal origin, Terminal destination){
        ID = id;
        _origin = origin;
        _destination = destination;
    }
}
