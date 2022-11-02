package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable{
    private static final long serialVersionUID = 202208091753L;
    private final int ID;

    private boolean _isPaid;
    private Terminal _origin;
    private Terminal _destination;

    private double _price;

    public Communication(int id,Terminal origin, Terminal destination, double price){
        ID = id;
        _origin = origin;
        _destination = destination;
        _isPaid = false;
        _price = price;
    }

    public int getId(){
        return ID;
    }

    public boolean isPaid(){
        return _isPaid;
    }

    public double getPrice(){
        return _price;
    }

}
