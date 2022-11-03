package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalState;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getTerminalState().isIdle()){
      _display.popup(Message.alreadyOn());
    }
    _receiver.getTerminalState().changeToIdle();
  }
}

