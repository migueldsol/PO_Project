package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

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
    } catch (UnknownTerminalKeyException ucke){
      throw new UnknownTerminalKeyException(friendID);
    }
  }
}
