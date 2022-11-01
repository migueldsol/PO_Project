package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.getTerminalState().canStartCommunication());
    addStringField("terminalId",Message.terminalKey());
    addOptionField("type",Message.commType(),"VIDEO","VOICE");
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
