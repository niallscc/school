package cs460.webdnd.client.commands.editor;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IController;
//import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

/***
 * This command is used to Upload the the file(s) specified within the Upload
 * form
 * 
 * @author Theodore Schnepper
 * 
 */
public class FileUploadCommand implements ICommand {

	//private IModel model;
	private CState state;

	/***
	 * Creates the FileUploadCommand utilizing an IController. The IController
	 * is only usted to get access to the state.
	 * 
	 * @param cont
	 */
	public FileUploadCommand(IController cont) {
		//this.model = cont.getModel();
		this.state = Misc.getCState(cont);
	}

	@Override
	public void run() {
		if (state == null)
			return;
		if (state.getUploadPanel() == null)
			return;
		state.getUploadPanel().submit();
	}

}
