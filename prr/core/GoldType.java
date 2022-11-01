package prr.core;

public class GoldType extends ClientType{

    public GoldType(Client client){
        super("GOLD",client);
    }
    
    public int getTextTarrif(int length){
        return super.getClient().getPricingSystem().getGoldTextTariff(length);
    }

    public int getVoiceTarrif(int duration){
        return super.getClient().getPricingSystem().getGoldVoiceTariff(duration);
    }

    public int getVideoTarrif(int duration){
        return super.getClient().getPricingSystem().getGoldVideoTariff(duration);
    }

    public void changeType(){
        //FIXME implementar faltam cenas das communications
    }

}
