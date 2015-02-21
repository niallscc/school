package cs460.webdnd.client.commands.servercommunication;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.UpdateImageListCommand;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.handlers.MoveTemplateHandler;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.client.utilities.serialization.SerializerWebpage;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.FileInformation.FileType;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * This is a command that permits the user to specify a project name to load
 * this project itself. This command does all of the 'heavy' lifting for loading
 * the project, and should be able to be accessed from anywhere.
 * 
 * @author Theodore Schnepper
 */
public class LoadProjectCommand implements ICommand {
	protected String projectName;
	protected CState state;
	protected IModel model;

	public LoadProjectCommand(String projectName, IModel model) {
		this.projectName = projectName;
		this.model = model;
		this.state = Misc.getCState(model);
	}

	@Override public void run() {
		if (state == null) return;
		state.getProjectService().setProject(projectName,new CallbackChangeProject());
	}

	/**
	 * This is the callback for loading the project. If it succeeded, we will
	 * proceed to query the existing files to load the project, automatically.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackChangeProject implements AsyncCallback<Response<Boolean>> {

		@Override public void onFailure(final Throwable caught) {
			SC.say("Could Not Contact the Server to Change Project.");
		}

		@Override public void onSuccess(final Response<Boolean> result) {
			if (result == null || result.getResponse() != Response.Res.ALL_OK) {
				SC.say("Unable to Successfully load the project " + projectName);
				return;
			}
			state.getDataService().getFiles(new CallbackFilterUploadedFiles());
			model.run(new UpdateImageListCommand(model));
		}
	}

	/**
	 * This is the callback used to filter all the uploaded files, getting only
	 * the saved serialized files, provided that they exist.
	 * 
	 * @author Theodore Schnepper
	 */
	protected class CallbackFilterUploadedFiles implements AsyncCallback<Response<LinkedList<FileInformation>>> {
		@Override public void onFailure(final Throwable caught) {
			SC.say("Could Not Contact the Server to Load Files.");
		}
		
		private void removeViews(Canvas page){
			if(page instanceof IView)
				model.removeView((IView)page);
			for(Canvas c : page.getChildren()){
				removeViews(c);
			}
		}

		@Override public void onSuccess(final Response<LinkedList<FileInformation>> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Did Not Recieve File Information: \""+result.getResponse()+"\"");
				return;
			}

			state.getTemplate().restore();

			model.clearCommandStacks(); // Make sure to clear our commands
			state.getPages().clear();   // Clear all of our current pages

			state.setSelection(state.getForm()); // Ensure we move our selection
											     // out of the way
			state.update();
			state.setSelection(null);
			state.update();
			
			if(state.getImageList() != null){
				for(Img i : state.getImageList()){ //Clear all Images
					i.destroy();
				}
				
				state.getImageList().clear();
			}
			
			for(WebPage page : state.getPages()){
				removeViews(page);  //Remove all current Views related to our Pages
			}
			
			removeViews(state.getTemplate()); //Remove all current views related to Templates

			if (state.getMenuEditor()==null) return;

			final TabSet ts = state.getMenuEditor().getTabSet();
			if (ts == null) return;

			for (final Tab t : ts.getTabs()) { // Delete our current Tabs
				//if(t.getTitle().equalsIgnoreCase("index")) continue;
				if (t.getTitle().equalsIgnoreCase("template")) continue;
				if (t.getTitle().equalsIgnoreCase("new tab")) continue;
				ts.removeTab(t);
				state.getTemplate().restore();
			}

			for (FileInformation f : result.getFile()) {
				if (f.getType() == FileType.BIN) { // Only consider our binary files for serialized info
					state.getDataService().getText(f.getFileName(),new CallbackDeserializeFile(f.getFileName()));
				}
			}
		}
	}

	/**
	 * After receiving the file information we will deserialize all the
	 * information and then load the newly created Pages into the Editing area,
	 * to restore the previously saved working conditions.
	 * 
	 * @author Theodore Schnepper
	 */
	private class CallbackDeserializeFile implements AsyncCallback<Response<String>> {
		private String fileName;
		private boolean isTemplate;

		public CallbackDeserializeFile(String fileName) {
			String[] temp = fileName.split("\\.");
			this.fileName = temp[0];
			this.isTemplate = this.fileName.equalsIgnoreCase("template");
		}

		@Override public void onFailure(final Throwable caught) {
			SC.say("Unable to Contact Server to get File Contents");
		}

		@Override public void onSuccess(final Response<String> result) {
			if (result==null || result.getResponse()!=Res.ALL_OK) {
				SC.say("Recieved Nothing for File Contents. Unexpected Error");
				return;
			}
			// result should contain all saved WebPage Information
			if (state.getMenuEditor() == null) return;

			TabSet ts = state.getMenuEditor().getTabSet();
			if (ts == null) return;

			WebPage wp = SerializerWebpage.deserialize(result.getFile());

			if (isTemplate) {
				for (Tab t : ts.getTabs()) {
					if (t.getID().equalsIgnoreCase("template")) {
						t.setPane(wp); // reuse our current tab
						state.getTemplate().destroy();
						state.setTemplate(wp);
						break;
					}
				}

			} else {
				Tab newTab = new Tab();
				newTab.setTitle(fileName);
				newTab.setPane(wp);

				MoveTemplateHandler mth = new MoveTemplateHandler(wp,Misc.getCState(model));
				newTab.addTabSelectedHandler(mth);
				newTab.addTabDeselectedHandler(mth);

				state.getPages().add(wp);
				ts.addTab(newTab,ts.getNumTabs()-1);
			}
		}
	}
}
