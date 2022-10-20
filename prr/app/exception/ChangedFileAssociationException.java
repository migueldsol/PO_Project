package prr.app.exception;

import pt.tecnico.uilib.menus.CommandException;

public class ChangedFileAssociationException extends CommandException{
    /** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

    private static final String message = "Association file was altered: error";


	public ChangedFileAssociationException() {
		super(message);
	}
}
