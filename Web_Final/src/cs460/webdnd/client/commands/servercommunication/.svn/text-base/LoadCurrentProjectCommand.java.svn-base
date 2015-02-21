package cs460.webdnd.client.commands.servercommunication;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Loads the Currently Set Project, based on whatever the Server has it set as
 * currently.
 * 
 * @author Theodore Schnepper
 */
public class LoadCurrentProjectCommand implements ICommand {
	protected IModel model;

	public LoadCurrentProjectCommand(IModel model) {
		this.model = model;
	}

	@Override public void run() {
		CState state = Misc.getCState(model);
		if (state == null) return;

		state.getProjectService().getCurrentProjectName(new CallbackGetProjectName());
	}

	/**
	 * After Retrieving the Name of the Current Project, this makes a call to
	 * the Load Project Command, to reuse as much code as possible.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackGetProjectName implements AsyncCallback<Response<String>> {
		@Override public void onFailure(Throwable caught) {
			SC.say("Unable to Contact Server, Trying to Retrive Current Project Name.");

		}
		@Override public void onSuccess(Response<String> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Unable to Find Current Project Name: \""+result.getResponse()+"\"");
				return;
			}
			model.run(new LoadProjectCommand(result.getFile(), model));
		}
	}
}