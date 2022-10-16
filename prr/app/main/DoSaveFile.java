package prr.app.main;

import prr.app.exception.FileOpenFailedException;
import prr.core.NetworkManager;
import prr.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {
  private Form _formulario = new Form("nomeFicheiro");

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
    _formulario.addStringField("fileName",Message.newSaveAs());
  }
  
  @Override
  protected final void execute() throws CommandException{

    try {
      _receiver.save();
    } catch (MissingFileAssociationException mfe){
      try {
        _formulario.parse();
        String fileName = _formulario.stringField("fileName");
        _receiver.saveAs(fileName);
      } catch (IOException e){
        throw new FileOpenFailedException(e);
      }

    } catch (IOException e){
      throw new FileOpenFailedException(e);
    }
    
  } 
}