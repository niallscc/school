package cs460.webdnd.client.commands.editor;

import cs460.webdnd.client.CState;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;

import cs460.webdnd.client.elements.textContent.Text;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;

/**
 * This command allows text to be added into the current
 * selected item, it is no longer a separate entity.
 * 
 * @author niallschavez
 */
public class TextCommand implements ICommand {
	private IController cont;
	private Canvas parent;// editor;

	/**
	 * This method initializes textCommand, this is a helper class that
	 * instantiates a new Text Object.
	 * 
	 * @param cont
	 *            The main controller for the running state of the program
	 * @param parent
	 *            the currently selected canvas item.
	 */
	public TextCommand(IController cont, Canvas parent) {
		this.cont = cont;
		this.parent = parent;
	}

	@Override
	public void run() {
		CState state = Misc.getCState(cont);
		if (state == null) {
			SC.say("Please select something to add text to. ");
			return;
		}
		Text.edit(parent);
		parent.fireEvent(new RightMouseDownEvent(null));
	}
}