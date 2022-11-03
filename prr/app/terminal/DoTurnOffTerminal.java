package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalState;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
      //S substituir "SILENCE" pelo atributo da classe? É seguro?
    if(_receiver.getTerminalState().toString().equals("OFF")){
      _display.popup(Message.alreadyOff());
    }
    _receiver.getTerminalState().changeToOff();
  }
}
