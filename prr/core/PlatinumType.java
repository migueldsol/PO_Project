package prr.core;

public class PlatinumType extends ClientType{

    public PlatinumType(Client client){
        super("PLATINUM", client);
    }

    public double getTarrif(TextCommunication communication){
        return super.getClient().getPricingSystem().getPlatinumTextTariff(communication.getMessage().length());
    }
    public double getTarrif(InteractiveCommunication communication){
        return communication.isVideo() ? super.getClient().getPricingSystem().getPlatinumVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getPlatinumVoiceTariff(communication.getDuration());
    }

    public void changeType(){
        Client client = super.getClient();
        if (client.getBalance() < 0){
            client.setType(client.getNormalType());
        }
        else if (client.verifyPlatinumToGold()){
            client.setType(client.getPlatinumType());
        }
    }

}
