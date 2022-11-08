package prr.core;

import java.util.List;

public class PlatinumType extends ClientType{

    private static final String TYPE_NAME = "PLATINUM";

    public PlatinumType(Client client) {
        super(TYPE_NAME, client);
    }

    public double getTarrif(TextCommunication communication) {
        return super.getClient().getPricingSystem().getPlatinumTextTariff(communication.getMessage().length());
    }

    public double getTarrif(InteractiveCommunication communication) {
        return communication.isVideo() ? super.getClient().getPricingSystem().getPlatinumVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getPlatinumVoiceTariff(communication.getDuration());
    }

    public void changeType() {
        Client client = super.getClient();
        if (verifyDowngrade()) {
            client.setType(new NormalType(super.getClient()));
        } else if (verifyPlatinumToGold()) {
            client.setType(new GoldType(super.getClient()));
        }
    }

    public boolean verifyPlatinumToGold() {
        List<Communication> communications = getClient().getCommunicationsMade();
        if(communications.size() < 5){return false;}
            for (int i = (communications.size() - 1); i >= communications.size() - 3; i--) {
                if (communications.get(i).isVoice()|| communications.get(i).isVideo()) {
                    return false;
                }
            }
        return true;
    }

    @Override
    public boolean isPlatinum() {
        return true;
    }
}

