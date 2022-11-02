package prr.core;

public class TextCommunication extends Communication{
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
