package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("id", Message.commKey());
  }

  @Override
  protected final void execute() throws CommandException {
    int id = integerField("id");
    if (!_network.payCommunication(id,_receiver)) {
      _display.popup(Message.commKey());
    }
  }
}
