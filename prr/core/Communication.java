package prr.core;

import java.io.Serializable;

public abstract class Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private final int ID;

    private boolean _ended;

    private boolean _isPaid;
    private final Terminal ORIGIN;
    private final Terminal DESTINATION;

    private double _price;

    public Communication(int id, Terminal origin, Terminal destination) {
        ID = id;
        ORIGIN = origin;
        DESTINATION = destination;
        _isPaid = false;
        _ended = false;
    }

    public void endCommunication() {
        _ended = true;
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

    public void pay(){
        _isPaid = true;
    }

    public String getState(){
        return _ended ? "FINISHED" : "ONGOING";
    }
    public String getOriginId(){
        return ORIGIN.getKey();
    }

    public String getDestinationId(){
        return DESTINATION.getKey();
    }
}
