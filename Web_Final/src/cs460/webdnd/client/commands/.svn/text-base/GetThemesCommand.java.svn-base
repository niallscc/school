package cs460.webdnd.client.commands;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

public class GetThemesCommand implements ICommand {
	IModel model;
	ListBox projects; 
	public GetThemesCommand(IModel model, ListBox projects){
		this.model=model;
		this.projects=projects;
	}
	
	@Override
	public void run() {
		CState state = Misc.getCState(model);
		if(state == null)
			return;
		state.getSharedService().getThemes(new GetThemesCallBack(projects));
	}
	

	private class GetThemesCallBack implements AsyncCallback<Response<LinkedList<String>>> {
		private ListBox projects;

		public GetThemesCallBack(ListBox projects) {
			this.projects = projects;
		}

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Response<LinkedList<String>> result) {
			if(result.getResponse() != Res.ALL_OK){
				SC.say("Unablee to Retrieve Theme List: " + result.getResponse());
				return;
			}
			List<String> res = result.getFile();

			for (String name : res) {
				if(!name.equals("template"))
					projects.addItem(name);
			}

		}

	}
	
}