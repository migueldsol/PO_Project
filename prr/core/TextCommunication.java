package prr.core;

public class TextCommunication extends Communication{
    private String _message;

    public TextCommunication(int id, Terminal origin, Terminal destination, String message,double price) {
        super(id, origin, destination,price);
        _message = message;
    }

}
