package cs460.webdnd.client.commands.servercommunication;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.ResetWorkAreaCommand;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.FileInformation.FileType;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * This command is to be used to load the Initial Editor. This will mainly check
 * to see if the user has ever saved the project that is being opened. The only
 * way this can happen is if the user is brand new.
 * 
 * @author Theodore Schnepper
 */
public class InitialLoadCommand implements ICommand {
	protected IModel model;
	private CState state;

	public InitialLoadCommand(IModel model) {
		this.model = model;
		this.state = Misc.getCState(model);
	}

	@Override public void run() {
		if (state == null) return;

		state.getDataService().getFiles(new CallbackFileCheck());
	}

	/**
	 * This Callback is utilized to check to see if there are any BIN files
	 * currently saved on the server.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackFileCheck implements AsyncCallback<Response<LinkedList<FileInformation>>> {
		@Override public void onFailure(final Throwable caught) {
			SC.say("Unable to Contact Server While trying to Retrive File List.");
		}
		@Override public void onSuccess(final Response<LinkedList<FileInformation>> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				if(result != null)
					SC.say("Unexpected Result Returned When Retrieving File List: \""+result.getResponse()+"\"");
				return;
			}
			boolean hasFiles = false;
			for (FileInformation f : result.getFile()) {
				hasFiles |= (f.getType()==FileType.BIN);
			}

			if (!hasFiles) {
				model.run(new ResetWorkAreaCommand(model));
				model.run(new SaveProjectCommand(model));
			} else {
				model.run(new LoadCurrentProjectCommand(model));
			}
		}
	}
}