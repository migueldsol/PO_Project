package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    Collection <Client> clientList = _receiver.getAllClients();
    for (Client i : clientList){
      _display.addLine(i.toString());
      _display.display();
    }
  }
}
