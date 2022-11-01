package prr.core;

public class NormalType extends ClientType{

    public NormalType(Client client){
        super("NORMAL", client);
    }
    
    public int getTextTarrif(int length){
        return super.getClient().getPricingSystem().getNormalTextTariff(length);
    }

    public int getVoiceTarrif(int duration){
        return super.getClient().getPricingSystem().getNormalVoiceTariff(duration);
    }

    public int getVideoTarrif(int duration){
        return super.getClient().getPricingSystem().getNormalVideoTariff(duration);
    }

    public void changeType(){
        if (super.getClient().getBalance() > 500){
            super.getClient().setType(super.getClient().getGoldType());
        }
    }
}
