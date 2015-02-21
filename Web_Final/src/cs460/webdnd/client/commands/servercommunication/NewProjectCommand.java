package cs460.webdnd.client.commands.servercommunication;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.ResetWorkAreaCommand;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * This Command will attempt to create a new project and provide the project the
 * specified name.
 * 
 * @author Theodore Schnepper
 */
public class NewProjectCommand implements ICommand {
	protected IModel model;
	protected String projectName;
	private CState state;

	public NewProjectCommand(String projectName, IModel model) {
		this.model = model;
		this.projectName = projectName;
		this.state = Misc.getCState(model);
	}

	@Override public void run() {
		if (state == null) return;

		state.getProjectService().newProject(new CallbackNewProject());
	}

	/**
	 * This returns the result of attempting to create a new Project. If a new
	 * project is created, then it will attempt to name the project.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackNewProject implements AsyncCallback<Response<Boolean>> {
		@Override public void onFailure(Throwable caught) {
			SC.say("Failed to Contact Server When Creating a New Project");
		}

		@Override public void onSuccess(Response<Boolean> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Unable to Create new Project. \""+result.getResponse()+"\"");
				return;
			}
			model.run(new NameProjectCommand(projectName, model, new ResetWorkAreaCommand(model)));
		}
	}
}