package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalState;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    //QUESTION substituir "SILENCE" pelo atributo da classe? É seguro?
    if(_receiver.getTerminalState().toString().equals("SILENCE")){
      _display.popup(Message.alreadySilent());
    }
    _receiver.setState(_receiver.getSilence());
  }
}

