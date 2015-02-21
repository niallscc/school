package cs460.webdnd.client.commands;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.NewTabCommand;
import cs460.webdnd.client.commands.editor.SelectCommand;
import cs460.webdnd.client.commands.servercommunication.SaveProjectCommand;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

/***
 * This is a command that is to be used to reset the Working area back to a
 * 'default' state. This is to be used whenever new project is called. It will
 * automatically save the changes afterwards to ensure that we load correctly
 * whenever a new project is created.
 * 
 * @author Theodore Schnepper
 */
public class ResetWorkAreaCommand implements ICommand {
	private IModel model;
	private CState state;

	/***
	 * This will create the ResetWorkAreaCommand. It utilizes the IModel as it
	 * needs to be able to run other commands, as well as have access to the
	 * state.
	 * 
	 * @param model
	 *            The IModel this command is being run on.
	 */
	public ResetWorkAreaCommand(IModel model) {
		this.model = model;
		this.state = Misc.getCState(model);
	}

	@Override
	public void run() {
		if (state == null)
			return;

		if (state.getMenuEditor() == null)
			return;

		if (state.getTemplate() == null)
			return;

		state.getTemplate().restore();
		model.run(new SelectCommand(state, null)); // Select Nothing (Update accordingly)
		model.clearCommandStacks();

		TabSet ts = state.getMenuEditor().getTabSet();
		
		boolean indexFound = false;
		for (Tab t : ts.getTabs()) {
			if (t.getTitle().equalsIgnoreCase("index")) {
				indexFound = true;
				state.getPages().clear();
				t.getPane().destroy();
				ts.updateTab(t, null);
				ts.removeTab(t);
				model.run(new NewTabCommand("index", model));

				// WebPage index = new WebPage("index");
				// t.setPane(index);
				// state.getPages().add(index);
			} else if (t.getTitle().equalsIgnoreCase("template")) {
				WebPage template = new WebPage("template");
				t.setPane(template);
				state.getTemplate().destroy();
				state.setTemplate(template);
			} else if (t.getTitle().equalsIgnoreCase("new tab")) {
				continue; // Nothing
			} else {
				ts.removeTab(t);
			}
		}
		
		if(!indexFound){
			model.run(new NewTabCommand("index", model));
		}

		model.run(new SaveProjectCommand(model));
	}
}