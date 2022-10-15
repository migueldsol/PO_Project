package prr.app.main;

import prr.core.NetworkManager;
import prr.core.exception.ImportFileException;
import prr.app.exception.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("fileName", "Insert file name: ");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String fileName = stringField("fileName");
    try {
      _receiver.importFile(fileName);
    } catch (ImportFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
