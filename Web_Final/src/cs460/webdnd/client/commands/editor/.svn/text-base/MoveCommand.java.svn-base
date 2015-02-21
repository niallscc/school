package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/***
 * A <code>MoveCommand</code> is an <code>IUndoableCommand</code> which controls
 * where to move a Canvas. The command can be undone, and as such it keeps
 * information relativent to it's previous location so it can be recalled when
 * needed.
 * 
 * @author Theodore Schnepper
 * 
 */
public class MoveCommand implements IUndoableCommand {

	private Canvas item;
	private String oldX;
	private String oldY;
	private String newX;
	private String newY;

	/***
	 * The MoveCommand needs to be created with it's old coordinates, it's new
	 * coordinates, as well as the Canvas being moved.
	 * 
	 * @param item
	 *            the Canvas to be moved.
	 * @param oldX
	 *            the old x coordinate of the Canvas, before the move.
	 * @param oldY
	 *            the old y coordinate of the Canvas, before the move.
	 * @param newX
	 *            the new x coordinate of the Canvas, after the move.
	 * @param newY
	 *            the new y coordinate of the Canvas, after the move.
	 */
	public MoveCommand(Canvas item, String oldX, String oldY, String newX,
			String newY) {
		this.item = item;
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
	}

	@Override
	public void run() {
		item.setLeft(newX);
		item.setTop(newY);
	}

	@Override
	public void undo() {
		item.setLeft(oldX);
		item.setTop(oldY);
	}

	@Override
	public void finish() {
		return;

	}

}
