package prr.app.lookup;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Network;
import prr.core.exception.KeyNotFoundException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

  DoShowCommunicationsFromClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
    addStringField("clientId",Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String clientId = stringField("clientId");
    try {
      _display.popup(_receiver.showCommunicationsFromClient(clientId));
    }catch (KeyNotFoundException knfe){
      throw new UnknownClientKeyException(clientId);
    }
  }
}
