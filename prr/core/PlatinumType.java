package prr.core;

public class PlatinumType extends ClientType{

    public PlatinumType(Client client){
        super("PLATINUM", client);
    }
    
    public int getTextTarrif(int length){
        return super.getClient().getPricingSystem().getPlatinumTextTariff(length);
    }

    public int getVoiceTarrif(int duration){
        return super.getClient().getPricingSystem().getPlatinumVoiceTariff(duration);
    }

    public int getVideoTarrif(int duration){
        return super.getClient().getPricingSystem().getPlatinumVideoTariff(duration);
    }

    public void changeType(){
        //FIXME implementar, faltam communications
    }

}
