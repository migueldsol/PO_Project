package prr.core;

import java.io.Serializable;
import java.util.List;

public class PlatinumType extends ClientType implements Serializable {

    private static final long serialVersionUID = 202208091753L;

    public PlatinumType(Client client) {
        super("PLATINUM", client);
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
            client.setType(client.getNormalType());
        } else if (verifyPlatinumToGold()) {
            client.setType(client.getPlatinumType());
        }
    }

    public boolean verifyPlatinumToGold() {
        List<Communication> communications = getClient().getCommunicationsMade();
        if(communications.size() < 5){return false;}
            for (int i = (communications.size() - 1); i >= communications.size() - 3; i--) {
                if (communications.get(i).isVoice() || communications.get(i).isText()) {
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

