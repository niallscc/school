package cs460.webdnd.client.commands.servercommunication;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.SelectCommand;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.client.utilities.serialization.SerializerWebpage;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * This command attempts to save the Current Project to the Server.
 * 
 * @author Theodore Schnepper
 */
public class SaveProjectCommand implements ICommand {
	private IModel model;
	protected CState state;
	protected final String EXT = ".bin";

	public SaveProjectCommand(IModel model) {
		this.model = model;
		state = Misc.getCState(model);
	}

	@Override public void run() {
		if (state == null) return;
		if (state.getTemplate() == null) return;

		model.run(new SelectCommand(state, null));
		state.getTemplate().restore();
		
		state.getMenuEditor().getTabSet().selectTab(0);
		
		LinkedList<WebPage> pages = new LinkedList<WebPage>();
		for (WebPage p : state.getPages()) {
			//String serial = SerializerWebpage.serialize(p);
			pages.add(p);
			//state.getDataService().sendHtml(serial, p.getName() + ".bin", new CallbackSavePage(p.getName(),serial));
		}
		if(pages.size() > 0){
			WebPage p = pages.removeFirst();
			state.getDataService().sendHtml(SerializerWebpage.serialize(p), p.getName().concat(EXT), new CallbackSavePage(pages));
	
			
			WebPage template = state.getTemplate();
			state.getDataService().sendHtml(SerializerWebpage.serialize(template), template.getName().concat(EXT) , new CallbackSavePage(null));
		}
		//String serial = SerializerWebpage.serialize(state.getTemplate());
		//state.getDataService().sendHtml(
		//		SerializerWebpage.serialize(state.getTemplate()),
		//		state.getTemplate().getName() + ".bin",
		//		new CallbackSavePage(state.getTemplate().getName(), serial)
		//);
	}

	/**
	 * This contains handling for the result of saving the project. This is used
	 * solely for displaying 'helpful' error messages
	 * 
	 * @author Theodore Schnepper
	 */
	private class CallbackSavePage implements AsyncCallback<Response<Boolean>> {
		private LinkedList<WebPage> pages;
		private WebPage page;

		public CallbackSavePage(LinkedList<WebPage> pages) {
			this.pages = pages;
			if(pages != null && pages.size() > 0){
				this.page = pages.removeFirst();
			}
			
		}

		@Override public void onFailure(Throwable caught) {
			SC.say("Unable to Contact Server When Attempting to Save Page: \""+page.getName()+"\"");
		}

		@Override public void onSuccess(Response<Boolean> result) { 
			if ((result==null || result.getResponse()!=Res.ALL_OK)) {
				if(result != null){
					System.err.println("Attempt to Save Page: \"\" Failed: \""+result.getResponse()+"\"");
					SC.say("Attempt to Save Page: \""+page.getName()+"\" Failed: \""+result.getResponse()+"\"");
				}
			}
			else{
				if(page != null)
					state.getDataService().sendHtml(SerializerWebpage.serialize(page), page.getName().concat(EXT), new CallbackSavePage(pages));
			}
		}
	}
}