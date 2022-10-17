package prr.app.client;

import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("clientID", Message.key());
    addStringField("clientName", Message.name());
    addIntegerField("clientTaxNumber", Message.taxId());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientID = stringField("clientID");
    String clientName = stringField("clientName");
    Integer clientTaxNumber = integerField("clientTaxNumber");

    if (!_receiver.registerClient(clientID,clientName,clientTaxNumber)){
      throw new DuplicateClientKeyException(clientName);
    }
  }
}

