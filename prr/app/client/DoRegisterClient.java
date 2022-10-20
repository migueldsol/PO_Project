package prr.app.client;

import prr.core.Network;
import prr.core.exception.ClientKeyAlreadyExistsException;
import prr.app.exception.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

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

    try{
      _receiver.registerClient(clientID,clientName,clientTaxNumber);
    } catch (ClientKeyAlreadyExistsException ckaee){
        throw new DuplicateClientKeyException(clientID);
    }
  }
}

