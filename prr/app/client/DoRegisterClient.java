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
    addStringField("clientID", "Insert client's ID: ");
    addStringField("clientName", "Insert client's name: ");
    addIntegerField("clientTaxNumber", "Insert client's tax number: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String clientID = stringField("clientID");
    String clientName = stringField("clientName");
    Integer clientTaxNumber = integerField("clientTaxNumber");

    try{
      _receiver.registerClient(clientID,clientName,clientTaxNumber);
    } catch (DuplicateClientKeyException message) {
      _display.popup(message);
    }
    _display.popup("Client registered");
    }
    /*
    if (_receiver.registerClient(clientID,clientName,clientTaxNumber)){
      message = "Client registered";
    }
    else {message = "Client not registered";}
    _display.popup(message); 
  }
     */
}

