package prr.core;

import java.util.List;

public class GoldType extends ClientType{

    private static final String TYPE_NAME = "GOLD";
    public GoldType(Client client) {
        super(TYPE_NAME, client);
    }

    public double getTarrif(TextCommunication communication) {
        return super.getClient().getPricingSystem().getGoldTextTariff(communication.getMessage().length());
    }

    public double getTarrif(InteractiveCommunication communication) {
        return communication.isVideo() ? super.getClient().getPricingSystem().getGoldVideoTariff(communication.getDuration()) : super.getClient().getPricingSystem().getGoldVoiceTariff(communication.getDuration());
    }

    public void changeType() {
        Client client = super.getClient();
        if (verifyDowngrade()) {
            client.setType(client.getNormalType());
        } else if (verifyGoldToPlatinum()) {
            client.setType(client.getPlatinumType());
        }
    }

    public boolean verifyGoldToPlatinum() {
        List<Communication> communications = getClient().getCommunicationsMade();
        if(communications.size() < 5){return false;}
            for (int i = (communications.size()-1); i >= communications.size() - 5; i--) {
                if (communications.get(i).isVoice() || communications.get(i).isText()) {
                    return false;
                }
            }
        return true;
    }

    @Override
    public boolean isGold() {
        return true;
    }
}
