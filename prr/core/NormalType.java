package prr.core;

public class NormalType extends ClientType{

    public NormalType(Client client){
        super("NORMAL", client);
    }

    public double getTarrif(TextCommunication communication){
        return super.getClient().getPricingSystem().getNormalTextTariff(communication.getMessage().length());
    }
    public double getTarrif(InteractiveCommunication communication){
        return communication.isVideo() ? super.getClient().getPricingSystem().getNormalVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getNormalVoiceTariff(communication.getDuration());
    }

    public void changeType(){
        if (super.getClient().getBalance() > 500){
            super.getClient().setType(super.getClient().getGoldType());
        }
    }
}
