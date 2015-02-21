package cs460.webdnd.client.commands.editor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.client.utilities.html_converter.Converter;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

public class RenderCommand implements ICommand {
	private String origin;
	private CState state;
	private IController cont;
	protected final String EXT = ".html";
	private List<String> css_file_names;

	/**
	 * This is essentially a boolean that either displays the website if you
	 * want to render or doesn't if you want to get a zip.
	 * 
	 * @param origin
	 *            the page to maybe display
	 */
	public RenderCommand(String origin, IController cont) {
		this.origin = origin;
		this.cont = cont;

	}

	@Override
	public void run() {
		state = Misc.getCState(cont);

		if (state == null)
			return;

		ArrayList<String> out = new ArrayList<String>();
		System.out.println("*****************************************");
		
		css_file_names = new LinkedList<String>();
		css_file_names.add("BorderColor.css");
		css_file_names.add("BorderRadius.css");
		css_file_names.add("BorderStyle.css");
		css_file_names.add("BorderWidth.css");
		css_file_names.add("MenuStyles.css");
		css_file_names.add("BackgroundStyles.css");

		LinkedList<WebPage> pages = new LinkedList<WebPage>();
		for (WebPage page : state.getPages()) {
			pages.add(page);
		}
		
		WebPage page = pages.removeFirst();
		convert(page, pages);
		
		
			
		TabSet ts = state.getMenuEditor().getTabSet();
		if (origin.equalsIgnoreCase("render"))
			Window.open(state.getUploadActionURL() + "?file="
					+ ((WebPage) ts.getSelectedTab().getPane()).getName()
					+ ".html", "_blank", "");

		state.getMenuEditor().getTabSet().selectTab(0);

	}

	protected void convert(WebPage page, LinkedList<WebPage> pages){
		System.out.println("Attempting to convert page: \""+page+"\".");
		String baseurl = "/CSS/";
		String output = new Converter(page, state.getTemplate(), baseurl, css_file_names).generate();

		//page.restore();
		state.getTemplate().restore();
		System.out.println(output);
		state.getDataService().sendHtml(output, page.getName() + ".html", new SaveCallBack(pages));
		System.out.println("*****************************************");
		//out.add(output);
	}
	
	protected class SaveCallBack implements AsyncCallback<Response<Boolean>> {
		private LinkedList<WebPage> pages;
		private WebPage page;
		
		public SaveCallBack(LinkedList<WebPage> pages){
			this.pages = pages;
			if(pages != null && pages.size() > 0)
				this.page = pages.removeFirst();
		}
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error on Save:");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result == null || result.getResponse() != Res.ALL_OK){
				if(result != null)
					SC.say("Unable to Save File to Server: " + result.getResponse());
			}
			else{
				if(page != null){
					convert(page, pages);
				}
			}
		}
	}
}