package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.KeyNotFoundException;
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
    String terminalKey = stringField("terminalId");
    String type = stringField("type");
    try{
      String message = _network.startInteractiveCommunication(_receiver,terminalKey,type);
      if(message == "OFF"){
        _display.popup(Message.destinationIsOff(terminalKey));
      }
      else if(message == "BUSY"){
        _display.popup(Message.destinationIsBusy(terminalKey));
      }
      else if(message == "SILENCE"){
        _display.popup(Message.destinationIsSilent(terminalKey));
      }
    } catch (KeyNotFoundException knfe){
      throw new UnknownTerminalKeyException(terminalKey);
    }
  }
}
