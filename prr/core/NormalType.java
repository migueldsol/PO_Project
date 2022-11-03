package prr.core;

import java.io.Serializable;

public class NormalType extends ClientType implements Serializable {

    private static final long serialVersionUID = 202208091753L;
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

    @Override
    public boolean isNormal() {
        return true;
    }
}
