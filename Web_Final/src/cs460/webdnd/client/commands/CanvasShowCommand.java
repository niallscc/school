package cs460.webdnd.client.commands;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.ICommand;

/**
 * This command is used to show the specified Canvas. This command is not
 * undoable as the user doesn't have direct control over it's invocation.
 * 
 * @author Theodore Schnepper
 */
public class CanvasShowCommand implements ICommand {

	private Canvas item;

	/**
	 * This creates the command that allows for the specified item to be shown.
	 * 
	 * @param item
	 *            the Canvas to show.
	 */
	public CanvasShowCommand(Canvas item) {
		this.item = item;
	}

	@Override
	public void run() {
		item.show();
	}
}