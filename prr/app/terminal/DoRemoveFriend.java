package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("terminalID",Message.clientKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String friend = stringField("terminalID");
    try{
      _network.removeFriend(_receiver.getKey(),friend);
    } catch(UnknownTerminalKeyException utke){
      throw new UnknownTerminalKeyException(friend);
    }
  }
}
