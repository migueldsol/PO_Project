package prr.app.terminals;

import prr.app.exception.DuplicateClientKeyException;
import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.TerminalType;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("terminalKey","Insert terminal's ID:");
    addStringField("terminalType","Insert Terminal's Type: (BASIC or FANCY) ");
    addStringField("clientID" ,"Insert client:");
  }

  @Override
  protected final void execute() throws CommandException {
    String terminalID = stringField("terminalKey");
    TerminalType terminalType = TerminalType.valueOf(stringField("terminalType"));
    String clientID = stringField("clientID");

    try{
      _receiver.registerTerminal(terminalID,terminalType,clientID);
    } catch(InvalidTerminalKeyException | DuplicateTerminalKeyException message){
        _display.popup(message);
    }

    _display.popup("Terminal registered");

  }
}
