package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private final int ID;

    private boolean _ended;

    private boolean _isPaid;
    private Terminal _origin;
    private Terminal _destination;

    private double _price;

    public Communication(int id, Terminal origin, Terminal destination) {
        ID = id;
        _origin = origin;
        _destination = destination;
        _isPaid = false;
        _ended = false;
    }

    public void endInteractiveCommunication() {
        _ended = true;
    }

    public boolean hasEnded() {
        return _ended;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public int getId() {
        return ID;
    }

    public boolean isPaid() {
        return _isPaid;
    }

    public double getPrice() {
        return _price;
    }

    public void discount() {
        _price *= 0.5;
    }

    public abstract String toString();

    public boolean isText(){
        return false;
    }
    
    public boolean isVoice(){
        return false;
    }

    public boolean isVideo(){
        return false;
    }
}
