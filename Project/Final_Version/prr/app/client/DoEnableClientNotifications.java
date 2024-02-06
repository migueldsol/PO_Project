package prr.app.client;

import prr.core.Network;
import prr.core.exception.KeyNotFoundException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientID = stringField("ClientID");
    
    try{
      if (! _receiver.TurnOnNotification(clientID)){  
        _display.popup(Message.clientNotificationsAlreadyEnabled()); 
      } 
    } catch (KeyNotFoundException knfe){
      throw new UnknownClientKeyException(clientID);
    }
  }
}
