package prr.core.exception;

public class KeyNotFoundException extends Exception{
    
    private static final String ERROR_MESSAGE = "Key not found: ";

    public KeyNotFoundException(String key){
        super(ERROR_MESSAGE + key);
    }

    public KeyNotFoundException(String key, Throwable cause){
        super(ERROR_MESSAGE + key, cause);
    }
}
