package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.KeyNotFoundException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("friendID",Message.clientKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String friendID = stringField("friendID");
    try{
      _network.addFriend(_receiver.getKey(),friendID);
    } catch (KeyNotFoundException ucke){
      throw new UnknownTerminalKeyException(friendID);
    }
  }
}
