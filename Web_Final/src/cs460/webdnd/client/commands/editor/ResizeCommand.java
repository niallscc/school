package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/***
 * This command is meant to resize the specified Canvas by the specified amount
 * The <code>ResizeCommand</code> also keeps track it's previous values, just in
 * case it needs to restore them with an undo.
 * 
 * @author Theodore Schnepper
 * 
 */
public class ResizeCommand implements IUndoableCommand {

	private String oldHeight;
	private String oldWidth;
	private String oldx;
	private String oldy;
	private String newHeight;
	private String newWidth;
	private String newx;
	private String newy;
	private Canvas item;

	/***
	 * This creates the new ResizeCommand and maintains information so that it
	 * can be undone should the user want to undo this command.
	 * 
	 * @param item
	 *            The Canvas that is being resized
	 * @param oldWidth
	 *            The initial Width of the Canvas before it is resized
	 * @param oldHeight
	 *            The initial Height of the Canvas before it is resized
	 * @param newWidth
	 *            The ending Width of the Canvas after it has been resized
	 * @param newHeight
	 *            The ending Height of the Canvas after it has been resized
	 */
	public ResizeCommand(
			Canvas item,
			String oldWidth, String oldHeight, String oldx, String oldy,
			String newWidth, String newHeight, String newx, String newy
	) {
		this.item = item;
		this.oldWidth = oldWidth;
		this.oldHeight = oldHeight;
		this.newWidth = newWidth;
		this.newHeight = newHeight;
		this.oldx = oldx;
		this.oldy = oldy;
		this.newx = newx;
		this.newy = newy;
	}

	@Override
	public void run() {
		item.setWidth(newWidth);
		item.setHeight(newHeight);
		if(item.getPosition() != Positioning.RELATIVE){
			item.setLeft(newx);
			item.setTop(newy);
		}
	}

	@Override
	public void undo() {
		item.setWidth(oldWidth);
		item.setHeight(oldHeight);
		item.setLeft(oldx);
		item.setTop(oldy);
	}

	@Override
	public void finish() {
		return;

	}

}
