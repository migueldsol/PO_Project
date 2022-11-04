package prr.core.exception;

public class OriginUnsuportedCommunicationException extends Exception{
    
    private String _terminalId;
    private String _communicationType;

    public OriginUnsuportedCommunicationException(String TerminalId, String communicationType){
        super();
        _terminalId = new String(TerminalId);
        _communicationType = new String(communicationType);
    }

    public String getTerminalId(){
        return new String(_terminalId);
    }

    public String getCommunicationType(){
        return new String(_communicationType);
    }
}
