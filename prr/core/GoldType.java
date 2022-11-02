package prr.core;

public class GoldType extends ClientType{

    //QUESTIONS coloco isto em singleton?
    // also faz sentido no Client ter todas as instancias de ClientType diferentes?
    public GoldType(Client client){
        super("GOLD",client);
    }

    public double getTarrif(TextCommunication communication){
        return super.getClient().getPricingSystem().getGoldTextTariff(communication.getMessage().length());
    }
    public double getTarrif(InteractiveCommunication communication){
        return communication.isVideo() ? super.getClient().getPricingSystem().getGoldVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getGoldVoiceTariff(communication.getDuration());
    }

    public void changeType(){
        Client client = super.getClient();
        if (client.getBalance() < 0){
                client.setType(client.getNormalType());
        }
        else if (client.verifyGoldToPlatinum()){
            client.setType(client.getPlatinumType());
        }
    }

}
