/**
 * 
 */
package cs460.webdnd.client.elements.menuStyles;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.tab.Tab;
import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;

/**
 * @author niallschavez
 */
public class NavMenu extends Canvas implements IView {
	/**
	 * menu is the container that holds all of it together. stylename is the
	 * name of the styles that will be used for this menu
	 */
	ArrayList<String> names;
	CState state;
	int numItems = 0;

	/**
	 * This constructor gets passed in the style name for the desired nav menu
	 * 
	 * @param styleName
	 *            the name of the style sheet to be used.
	 */
	public NavMenu(String styleName, IController cont) {
		state = (CState) cont.getModel().getState();
		cont.getModel().addView(this);
		SelectOnClickHandler.giveSelectHandler(this);
		this.setStyleName(styleName);
		makeMenu();
	}


	private void makeMenu() {
		String html = "<ul>";
		names = new ArrayList<String>();
		for (Tab tab : state.getMenuEditor().getTabSet().getTabs()) {
			++numItems;

			if (!tab.getTitle().equalsIgnoreCase("template")&& !tab.getTitle().equalsIgnoreCase("New Tab")) {
				
				html += "<li><a href=\"/web_final/FileServlet?file="
						+ tab.getTitle()
						+ ".html\">"
						+ tab.getTitle()
						+ "</a></li>";
				names.add(tab.getTitle());
			}
		}
		html += "</ul>";
		setContents(html);
	}

	@Override
	public void Update(IState istate) {
		//Tab[] tabs = state.getMenuEditor().getTabSet().getTabs();
		boolean changed = false;
		List<WebPage> pages = state.getPages();
		
		for(int i = 0; i < pages.size(); i++){
			boolean found = false;
			for(int j = 0; j < names.size(); j++){
				if(names.get(j).equalsIgnoreCase(pages.get(i).getName())){
					found = true;
					break;
				}
			}
			
			if(!found){
				names.add(i, pages.get(i).getName());
				changed = true;
			}
		}
		
		for(int i = 0; i < names.size(); i++){
			boolean found = false;
			for(int j = 0; j < pages.size(); j++){
				if(names.get(i).equalsIgnoreCase(pages.get(j).getName())){
					found = true;
					break;
				}
			}
			if(!found){
				names.remove(i);
				i--;
			}
		}
		
		if(changed)
			makeMenu();
		
		/**
		 * This gets executed when the user adds a tab and we need to add a new
		 * link to the bar
		 * 
		 */
		//if ((tabs.length) > numItems) {
		//	remake(tabs);
		//}
		/**
		 * This gets execute when the user has deleted a tab and we need to
		 * remove that link from the bar
		 */
		//else if ((tabs.length) < numItems) {
		//	remake(tabs);
		//}
		/**
		 * this checks if the user renamed any of their tabs and it updates
		 * accordingly
		 **/
		/*else {
			for (int i = 0; i < (tabs.length - 1); i++) {
				if (!names.get(i).equalsIgnoreCase(tabs[i].getTitle())) {
					remake(tabs);
					break;
				}
			}
		}*/
	}
	
	public void remake(Tab[] tabs) {
		names = new ArrayList<String>();
		numItems = 0;

		makeMenu();
	}
}