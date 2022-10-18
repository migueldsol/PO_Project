package prr.core.exception;

public class TerminalKeyAlreadyExistsException extends Exception{
    
    private static final String ERROR_MESSAGE = "Terminal's key already exists: ";

    public TerminalKeyAlreadyExistsException(String terminalKey){
        super(ERROR_MESSAGE + terminalKey);
    }

    public TerminalKeyAlreadyExistsException(String terminalKey, Throwable cause){
        super(ERROR_MESSAGE + terminalKey, cause);
    }
}
