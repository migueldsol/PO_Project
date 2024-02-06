package prr.app.terminals;

import prr.app.exception.DuplicateClientKeyException;
import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.TerminalType;
import prr.core.exception.InvalidSizeKey;
import prr.core.exception.KeyNotFoundException;
import prr.core.exception.TerminalKeyAlreadyExistsException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {
  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("terminalKey",Message.terminalKey());
    addOptionField("terminalType",Message.terminalType(),"BASIC","FANCY");
    addStringField("clientID" ,Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String terminalID = stringField("terminalKey");
    TerminalType terminalType = TerminalType.valueOf(optionField("terminalType"));
    String clientID = stringField("clientID");
    try{
      _receiver.registerTerminal(terminalID,terminalType,clientID);
    } catch(NumberFormatException|InvalidSizeKey nfe){
        throw new InvalidTerminalKeyException(terminalID);
    } catch(TerminalKeyAlreadyExistsException tkaee){
      throw new DuplicateTerminalKeyException(terminalID);
    } catch(KeyNotFoundException cknfe){
      throw new UnknownClientKeyException(clientID);
    }
  }
}
