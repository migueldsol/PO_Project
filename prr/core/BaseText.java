package prr.core;

public class BaseText {
    private final int VALUE_1;
    private final int VALUE_2;

    // VALUE_3 times the length 
    private final int VALUE_3;

    public BaseText(int value1, int value2, int value3){
        VALUE_1 = value1;
        VALUE_2 = value2;
        VALUE_3 = value3;
    }

    public int getTarrif(int length){
        if (length >= 100){
            return VALUE_3 * length;
        }    
        else if (length >= 50){
            return VALUE_2;
        }
        else {
            return VALUE_1;
        }    
    }
}
