package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalState;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.FailedInteractiveCommunicationException;
import prr.core.exception.KeyNotFoundException;
import prr.core.exception.OriginUnsuportedCommunicationException;
import prr.core.exception.TargetUnsuportedCommunicationException;
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
      _network.startInteractiveCommunication(_receiver,terminalKey,type);
    } catch (KeyNotFoundException knfe){
      throw new UnknownTerminalKeyException(terminalKey);
    } catch (OriginUnsuportedCommunicationException ouce){
      _display.popup(Message.unsupportedAtOrigin(ouce.getTerminalId(), ouce.getCommunicationType()));
    } catch (TargetUnsuportedCommunicationException tuce){
      _display.popup(Message.unsupportedAtDestination(tuce.getTerminalId(), tuce.getCommunicationType()));
    } catch (FailedInteractiveCommunicationException fice){
      TerminalState terminalState = fice.getTerminalState();
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
      _display.popup(Message.destinationIsBusy(terminalKey));
    }
  }
}
