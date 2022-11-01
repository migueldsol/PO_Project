package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.KeyNotFoundException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("terminalId",Message.terminalKey());
    addStringField("message",Message.textMessage());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String message = stringField("message");
    String targetTerminal = stringField("terminalId");
    try{
      if(!_network.textCommunication(_receiver,targetTerminal,message)){
        _display.popup(Message.destinationIsOff(targetTerminal));
      }
    }catch(KeyNotFoundException knfe){
      throw new UnknownTerminalKeyException(targetTerminal);
    }
  }
} 
