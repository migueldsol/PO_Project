package prr.core;

import java.io.Serializable;

public abstract class PricingSystem implements Serializable{

    private static final long serialVersionUID = 202208091753L;

    private final String PRICING_SYSTEM_NAME;

    public PricingSystem(String name){
        PRICING_SYSTEM_NAME = new String(name);
    }
    
    public abstract int getNormalTextTariff(int length);
    public abstract int getGoldTextTariff(int length);
    public abstract int getPlatinumTextTariff(int length);

    public abstract int getNormalVoiceTariff(int duration);
    public abstract int getGoldVoiceTariff(int duration);
    public abstract int getPlatinumVoiceTariff(int duration);

    public abstract int getNormalVideoTariff(int duration);
    public abstract int getGoldVideoTariff(int duration);
    public abstract int getPlatinumVideoTariff(int duration);

    public String getName(){
        return PRICING_SYSTEM_NAME;
    }
}
