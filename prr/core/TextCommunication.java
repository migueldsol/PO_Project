package prr.core;

import java.io.Serializable;

public class TextCommunication extends Communication implements Serializable {

    private static final long serialVersionUID = 202208091753L;
    private final String MESSAGE;

    public TextCommunication(int id, Terminal origin, Terminal destination, String message) {
        super(id, origin, destination);
        MESSAGE = message;
    }

    public String toString(){
        return "TEXT" + "|" + getId() + "|" + getOriginId() + "|" +
                getDestinationId() + "|" + MESSAGE.length() + "|" + Math.round(getPrice()) + "|" + getState();
    }
    @Override
    public boolean isText(){
        return true;
    }

    public String getMessage(){
        return MESSAGE;
    }


}
