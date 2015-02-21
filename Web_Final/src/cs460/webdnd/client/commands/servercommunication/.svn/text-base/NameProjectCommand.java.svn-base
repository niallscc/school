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
 * This command will attempt to change the name of the currently set project to
 * whatever the specified String is.
 * 
 * @author Theodore Schnepper
 */
public class NameProjectCommand implements ICommand {
	private CState state;
	protected String projectName;
	protected ICommand command;
	protected IModel model;

	/**
	 * Default constructor.
	 * 
	 * @param projectName
	 *            The Name to give the Project
	 * @param model
	 *            The current Running Model
	 */
	public NameProjectCommand(String projectName, IModel model) {
		this.projectName = projectName;
		this.state = Misc.getCState(model);
		this.command = null;
		this.model = model;
	}

	/**
	 * Alternate Constructor, includes optional ICommand to run after command is
	 * resolved.
	 * 
	 * @param projectName
	 *            The Name to give the project
	 * @param model
	 *            The current Running Model
	 * @param command
	 *            Optional. The command to run after this command has resolved.
	 */
	public NameProjectCommand(String projectName, IModel model, ICommand command) {
		this.projectName = projectName;
		this.state = Misc.getCState(model);
		this.command = command;
		this.model = model;
	}

	@Override public void run() {
		if (state == null) return;

		state.getProjectService().setProjectName(projectName, new CallbackChangeProjectName());
	}

	/**
	 * This callback handles the result of the NameChange call. This is solely
	 * used to display errors when attempting to change the name of a project.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackChangeProjectName implements AsyncCallback<Response<Boolean>> {
		@Override public void onFailure(Throwable caught) {
			SC.say("Unable to Contact Server to Change Project Name: \""+projectName+"\"");
		}
		@Override public void onSuccess(Response<Boolean> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Unable to Change Project Name: \""+result.getResponse()+"\"");
				return;
			}

			if (command != null) model.run(command);
		}
	}
}