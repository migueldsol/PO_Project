package prr.core;

public class TextCommunication extends Communication{
    private String _message;

    public TextCommunication(int id, Terminal origin, Terminal destination, String message) {
        super(id, origin, destination);
        _message = message;
    }

    public String toString(){
        return "TEXT";
    }

    @Override
    public boolean isText(){
        return true;
    }

}
