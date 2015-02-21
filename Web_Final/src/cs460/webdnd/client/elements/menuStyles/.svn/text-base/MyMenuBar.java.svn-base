/**
 * 
 */
package cs460.webdnd.client.elements.menuStyles;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import com.smartgwt.client.widgets.tab.Tab;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;

/**
 * We don't use this anymore, the new way is much much
 * better; see NavMenu.java.
 * 
 * @author niallschavez
 */
@Deprecated
public class MyMenuBar extends MenuBar implements IView {
	CState cstate;
	IModel model;

	public MyMenuBar(IController cont) {
		model = cont.getModel();
		model.addView(this);
		cstate = (CState) model.getState();
	}

	@Override
	public void Update(IState state) {
		Tab[] tabs = cstate.getMenuEditor().getTabSet().getTabs();

		if ((tabs.length - 2) > this.getItems().size()) {
			MenuItem item = new MenuItem(tabs[(tabs.length - 1)].getTitle(),
					new Command() {
						@Override
						public void execute() {
							// TODO figure out what each of these menu items
							// will do

						}
					});
			this.addItem(item);
		} else if ((tabs.length - 2) < this.getItems().size()) {
			int counter = 0;
			for (Tab tab : tabs) {
				if (counter < tabs.length - 1 && counter > 0)
					if (!tab.getTitle().equals(
							this.getItems().get(counter).getTitle())) {
						MenuItem i = this.getItems().get(counter);
						this.removeItem(i);
						this.Update(state);
						return;
					}
				counter++;
			}
		}/*
		 * else{ int counter=0; for( Tab tab: tabs){ if(counter < tabs.length -1
		 * && counter > 0 ){
		 * this.getItems().get(counter-1).setTitle(tab.getTitle());
		 * 
		 * return; } counter++; } }
		 */

	}
}
