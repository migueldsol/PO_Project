package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalState;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.FailedInteractiveCommunicationException;
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
    /*SOL a func startInteractiveCommunication devia receber as Strings
     e ir buscar os terminals la dentro para nao lidarmos com terminals na app*/
    String terminalKey = stringField("terminalId");
    String type = stringField("type");
    try{
      //QUESTIONS o receiver ser um terminal nao estraga a privacidade toda?
      _network.startInteractiveCommunication(_receiver,terminalKey,type);
    } catch (KeyNotFoundException knfe){
      throw new UnknownTerminalKeyException(terminalKey);
    } catch (FailedInteractiveCommunicationException fice){
      TerminalState terminalState = fice.getTerminalState();
      //QUESTION mexer com terminalState nao é uma fuga de privacidade?
      if(terminalState.isOff()){
        _display.popup(Message.destinationIsOff(terminalKey));
      }
      else if(terminalState.isBusy()){
        _display.popup(Message.destinationIsBusy(terminalKey));
      }
      else if(terminalState.isSilence()){
        _display.popup(Message.destinationIsSilent(terminalKey));
      }
    } catch (Exception e){
      //FIXME e suposto fazer algo qd o terminal liga a si mesmo?
      
    }
  }
}
