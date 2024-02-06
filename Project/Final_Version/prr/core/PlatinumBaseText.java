package prr.core;

public class PlatinumBaseText extends BaseText{


    public PlatinumBaseText(int value1, int value2, int value3) {
        super(value1, value2, value3);
    }
    
    public int getTarrif(int length){
        if (length >= 100){
            return super.getValue3();
        }    
        else if (length >= 50){
            return super.getValue2();
        }
        else {
            return super.getValue1();
        }    
    }
}
