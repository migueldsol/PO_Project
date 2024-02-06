package prr.core;

public class NormalType extends ClientType{

    private static final String TYPE_NAME = "NORMAL";
    public NormalType(Client client){
        super(TYPE_NAME, client);
    }

    public double getTarrif(TextCommunication communication){
        return super.getClient().getPricingSystem().getNormalTextTariff(communication.getMessage().length());
    }
    public double getTarrif(InteractiveCommunication communication){
        return communication.isVideo() ? super.getClient().getPricingSystem().getNormalVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getNormalVoiceTariff(communication.getDuration());
    }

    //FIXME isto pode ser um problema visto que só devemos fazer esta mudança depois de realizar um pagamento
    //deveriamos criar um changeTypeAfterPayment e changeTypeAfterCommunication
    public void changeType(){
        if (super.getClient().getBalance() > 500){
            super.getClient().setType(new GoldType(super.getClient()));
        }
    }

    @Override
    public boolean isNormal() {
        return true;
    }
}
