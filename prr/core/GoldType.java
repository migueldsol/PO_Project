package prr.core;

import java.io.Serializable;
import java.util.List;
import java.util.SortedMap;

public class GoldType extends ClientType implements Serializable {

    //QUESTIONS coloco isto em singleton?
    // also faz sentido no Client ter todas as instancias de ClientType diferentes?

    private static final long serialVersionUID = 202208091753L;
    public GoldType(Client client) {
        super("GOLD", client);
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
