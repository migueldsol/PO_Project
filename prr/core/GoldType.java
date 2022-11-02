package prr.core;

public class GoldType extends ClientType{

    //QUESTIONS coloco isto em singleton?
    // also faz sentido no Client ter todas as instancias de ClientType diferentes?
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
        Client client = super.getClient();
        if (client.getBalance() < 0){
                client.setType(client.getNormalType());
        }
        /*
        else if (client.verifyGoldToPlatinum()){

        }
        */
    }

}
