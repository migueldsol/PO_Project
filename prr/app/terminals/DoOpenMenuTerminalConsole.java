package prr.app.terminals;

import prr.core.Network;
import prr.core.exception.KeyNotFoundException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("terminalID",Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String terminalID = stringField("terminalID");
    try {
      (new prr.app.terminal.Menu(_receiver, _receiver.getTerminal(terminalID))).open();
    } catch(KeyNotFoundException knfe){
      throw new UnknownTerminalKeyException(terminalID);
    }
  }
}
