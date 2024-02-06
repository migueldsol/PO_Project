package prr.app.client;

import prr.core.Network;
import prr.core.exception.KeyNotFoundException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("ClientId", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    String clientId = stringField("ClientId");
    try{
      long payments = Math.round(_receiver.getClientPayments(clientId));
      long debts = Math.round(_receiver.getClientDebts(clientId));
      _display.popup(Message.clientPaymentsAndDebts(clientId, payments, debts));
    } catch (KeyNotFoundException knfe){
      throw new UnknownClientKeyException(clientId);
    }
  }
}