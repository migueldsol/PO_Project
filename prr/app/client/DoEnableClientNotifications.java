package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

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
    
    if (! _receiver.TurnOnNotification(clientID)){  //FIXME falta implementar esta funcionalidade no core
      _display.popup(Message.clientNotificationsAlreadyEnabled()); 
    } 
  }
}
