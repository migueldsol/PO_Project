package prr.core.exception;

public class InvalidSizeKey extends Exception{
    
    private static final String ERROR_MESSAGE = "Invalid client's key size: ";
    
    public InvalidSizeKey(String key){
        super(ERROR_MESSAGE + key);
    }

    public InvalidSizeKey(String key, Throwable cause){
        super(ERROR_MESSAGE + key, cause);
    }

}
