package cs460.webdnd.client.commands.editor;

/**
 * @author niallschavez
 * This command is for deleting a tab go figure right!? 
 */
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.servercommunication.DeleteFileCommand;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

public class DeleteTabCommand implements ICommand {
	private TabSet topTabSet;
	private CState state;
	private IModel model;

	/**
	 * 
	 * @param topTabSet
	 *            the tabset that comes from workspace
	 */
	public DeleteTabCommand(IModel model) {
		this.model = model;
		state = Misc.getCState(model);
		if(state != null)
			this.topTabSet = state.getMenuEditor().getTabSet();

	}

	/**
	 * Run grabs the top tab set, makes sure you aren't trying to delete add tab
	 * or template then if life is good, removes it
	 * 
	 */
	@Override
	public void run() {
		Tab toDelete = topTabSet.getSelectedTab();
		if (toDelete.getID().equals("template")
				|| toDelete.getID().equals("addTab")
				|| toDelete.getID().equals("index"))
			SC.say("Cannot remove the " + toDelete.getID() + " tab.");
		else {
			Canvas pane = toDelete.getPane();
			topTabSet.updateTab(toDelete, null);
			topTabSet.removeTab(toDelete);
			
			model.run(new DeleteFileCommand(((WebPage)pane).getName()+".bin", model));
	
			removeViews(pane);
			
			state.getPages().remove(pane); //Remove from list of pages
			pane.destroy(); //Ensure the contents are destroyed
		}
	}
	
	/**
	 * Resmoves any IView found in the specified Canvas, or it's children, from the
	 * Model.
	 * 
	 * @param c The Canvas to remove IViews from
	 */
	private void removeViews(Canvas c){
		if(c == null)
			return;
		
		if(c instanceof IView)
			model.removeView((IView)c);
		
		for(Canvas child : c.getChildren()){
			removeViews(child);
		}
		
	}
}
