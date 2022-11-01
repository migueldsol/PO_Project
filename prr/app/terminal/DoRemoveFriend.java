package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.KeyNotFoundException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("terminalID",Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String friend = stringField("terminalID");
    try{
      _network.removeFriend(_receiver.getKey(),friend);
    } catch(KeyNotFoundException utke){
      throw new UnknownTerminalKeyException(friend);
    }
  }
}
