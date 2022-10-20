package prr.core.exception;

public class ClientKeyAlreadyExistsException extends Exception{
    
    private static final String ERROR_MESSAGE = "Client's key already exists: ";

    public ClientKeyAlreadyExistsException(String clientKey){
        super(ERROR_MESSAGE + clientKey);
    }

    public ClientKeyAlreadyExistsException(String clientKey, Throwable cause){
        super(ERROR_MESSAGE + clientKey, cause);
    }
}
