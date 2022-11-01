package prr.core;


public interface PricingSystem{
    
    int getNormalTextTariff(int length);
    int getGoldTextTariff(int length);
    int getPlatinumTextTariff(int length);

    int getNormalVoiceTariff(int duration);
    int getGoldVoiceTariff(int duration);
    int getPlatinumVoiceTariff(int duration);

    int getNormalVideoTariff(int duration);
    int getGoldVideoTariff(int duration);
    int getPlatinumVideoTariff(int duration);

}
