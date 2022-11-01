package prr.app.client;

import prr.core.Network;
import prr.core.exception.KeyNotFoundException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientID = stringField("ClientID");

    try{
      if (! _receiver.turnOffNotification(clientID)){ 
        _display.popup(Message.clientNotificationsAlreadyDisabled()); 
      }
    } catch (KeyNotFoundException knfe){
      throw new UnknownClientKeyException(clientID);
    }
  }
}
