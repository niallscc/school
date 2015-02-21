package cs460.webdnd.client.commands.servercommunication;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tab.Tab;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.SharedServiceAsync;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.client.utilities.serialization.SerializerWebpage;
import cs460.webdnd.shared.Response.Res;
import cs460.webdnd.shared.Response;

/**
 * this command takes the selected template and loads it in to your project
 */
public class LoadTemplateCommand implements ICommand {
	private String template;
	protected IModel model;
	
	public LoadTemplateCommand(String template, IModel model) {
		this.template= template;
		this.model=model;
	}

	@Override
	public void run() {
		CState state = Misc.getCState(model);
		if(state == null)
			return;
		SharedServiceAsync service= Misc.getCState(model).getSharedService();
	
		service.getFile("template", template, new GetTemplateFile(model) );
		
	}

	protected class GetTemplateFile implements AsyncCallback<Response<String>>{
		private CState state;
		public GetTemplateFile(IModel model){
			this.state = Misc.getCState(model);
		}
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to Contact Server for Retrieving Template File: ");
		}

		@Override
		public void onSuccess(Response<String> result) {
			if(result.getResponse() != Res.ALL_OK){
				SC.say("Unable to Retrieve Template File:".concat(result.getResponse().toString()));
				return;	
			}
			WebPage template = SerializerWebpage.deserialize(result.getFile());
			
			for(Tab t : state.getMenuEditor().getTabSet().getTabs()){
				if(t.getTitle().equalsIgnoreCase("template")){
					t.setPane(template);
					break;
				}
			}
			state.getTemplate().destroy();
			state.setTemplate(template);
			
			model.run(new SaveProjectCommand(model));
		}
	}	
}