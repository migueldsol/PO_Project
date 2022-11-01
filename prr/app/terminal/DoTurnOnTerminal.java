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
    //QUESTIONS substituir "SILENCE" pelo atributo da classe? É seguro?
    if(_receiver.getTerminalState().toString().equals("IDLE")){
      _display.popup(Message.alreadyOn());
    }
    _receiver.setState(_receiver.getIdle());
  }
}

