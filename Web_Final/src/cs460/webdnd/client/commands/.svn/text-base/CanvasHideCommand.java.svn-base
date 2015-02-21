package cs460.webdnd.client.commands;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.ICommand;

/***
 * This command is used to hide the specified Canvas. It is not undoable, as
 * this isn't a command the user readily has access to.
 * 
 * @author Theodore Schnepper
 */
public class CanvasHideCommand implements ICommand {

	private Canvas item;

	/**
	 * To create the CanvasHideCommand the item to hide must be specified
	 * 
	 * @param item
	 *            the Canvas to hide.
	 */
	public CanvasHideCommand(Canvas item) {
		this.item = item;
	}

	@Override
	public void run() {
		item.hide();
	}
}