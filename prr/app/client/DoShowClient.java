package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
//FIXME add more imports if needed

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

    _display.popup(_receiver.toStringClient(clientID));
  }
}
