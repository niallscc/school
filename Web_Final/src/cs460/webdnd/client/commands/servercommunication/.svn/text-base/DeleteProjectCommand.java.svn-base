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
 * This command should represent a clean way to delete any specified project
 * regardless of the outcome.
 * 
 * @author Theodore Schnepper
 */
public class DeleteProjectCommand implements ICommand {
	protected IModel model;
	protected String projectName;
	protected CState state;

	public DeleteProjectCommand(final String projectName, final IModel model) {
		this.projectName = projectName;
		this.model = model;
		state = Misc.getCState(model);
	}

	@Override public void run() {
		if (state==null) return;

		state.getProjectService().getCurrentProjectName(new CallbackGetProjectName());
	}

	/**
	 * This merely checks to see if the current project is the project we are
	 * trying to delete. This is to check to see if we need to load after we
	 * delete the project.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackGetProjectName implements AsyncCallback<Response<String>> {
		@Override public void onFailure(final Throwable caught) {
			SC.say("Failed to Contact Server to Get Current Project Name");
		}

		@Override public void onSuccess(final Response<String> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Unable to Obtain Current Project Name: \""+result.getResponse()+"\"");
				return;
			}
			final String str = result.getFile();
			state.getProjectService().deleteProject(projectName,new CallbackDeleteProject(str.equalsIgnoreCase(projectName)));
		}
	}

	/**
	 * If deletion was successful, and the project was the current project, then
	 * we will load the first project the user has. Otherwise, we will do
	 * nothing.
	 * 
	 * @author Theodore Schnepper
	 */
	private class CallbackDeleteProject implements AsyncCallback<Response<Boolean>> {
		private boolean load;

		public CallbackDeleteProject(boolean load) {
			this.load = load;
		}

		@Override public void onFailure(final Throwable caught) {
			SC.say("Unable to Contact Server to Delete the Specified Project: \""+projectName+"\"");
		}

		@Override public void onSuccess(final Response<Boolean> result) {
			if (result == null || result.getResponse() != Response.Res.ALL_OK) {
				SC.say("Unable to Delete Project: \""+result.getResponse()+"\"");
				return;
			}
			if (load) {
				model.run(new LoadCurrentProjectCommand(model));
			}
		}
	}
}