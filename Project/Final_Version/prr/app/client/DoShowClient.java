package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("clientID",Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientID = stringField("clientID");
    
    String stringClient = _receiver.toStringClient(clientID);

    if (stringClient == null){
      throw new UnknownClientKeyException(clientID);
    }

    _display.addLine(_receiver.toStringClient(clientID));
    List <String> notifications = _receiver.toStringNotifications(clientID);
    if (notifications.size() > 0){
      _display.addAll(notifications);
    }
    _display.display();
  }
}
