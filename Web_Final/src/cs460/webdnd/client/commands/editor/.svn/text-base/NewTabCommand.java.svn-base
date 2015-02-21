package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.handlers.MoveTemplateHandler;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;

import cs460.webdnd.client.utilities.Misc;

/***
 * This Command is probably poorly named. This command creates a new WebPage to
 * add to the project, and adds it to the editor via a Tab. This command ensures
 * that all the appropriate listeners and references are updated to ensure the
 * existence of the page.
 * 
 * @author Theodore Schnepper
 * 
 */
public class NewTabCommand implements ICommand {
	private String title;
	private IModel model;

	/**
	 * 
	 * @param title
	 *            the title of the new tab
	 * @param a
	 *            reference to IModel
	 */
	public NewTabCommand(String title, IModel model) {
		this.title= title;
		
		this.model = model;
	}

	@Override
	public void run() {
		if (model.getState() instanceof CState) {
			final CState c = (CState) model.getState();
			final Tab temp = new Tab(title);
			WebPage page = new WebPage(title);

			CState state = Misc.getCState(model);
			if (state != null)
				state.getPages().add(page);

			temp.setPane(page);

			MoveTemplateHandler mHandler = new MoveTemplateHandler(page,
					Misc.getCState(model));
			temp.addTabSelectedHandler(mHandler);
			temp.addTabDeselectedHandler(mHandler);
			TabSet s = c.getMenuEditor().getTabSet();
			s.addTab(temp, (s.getTabs().length - 1));
		}
	}
}