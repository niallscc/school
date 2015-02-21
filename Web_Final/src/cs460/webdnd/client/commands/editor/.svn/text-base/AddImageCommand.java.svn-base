package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/***
 * This command adds an Image to the specified parent, this command assumes that
 * the Image Canvas has already been created, and merely acts as a intermediate
 * way to add the image canvas to the parent.
 * 
 * @author Theodore Schnepper
 * 
 */
public class AddImageCommand implements IUndoableCommand {

	private Canvas parent;
	private Canvas img;
	private boolean undone;

	/***
	 * Creates the Command, in order for the command to run successfully the
	 * parent, and img canvas must be provided.
	 * 
	 * @param parent
	 *            the parent to add the image to
	 * @param img
	 *            the image canvas to add to the parent.
	 */
	public AddImageCommand(Canvas parent, Canvas img) {
		this.parent = parent;
		this.img = img;
		this.undone = false;
	}

	@Override
	public void run() {
		if (img.getParentElement() == parent && !img.isVisible() && undone) {
			undone = false;
			img.show();
		} else {
			if (parent == null)
				return;
			parent.addChild(img);
		}

	}

	@Override
	public void undo() {
		img.hide();
		undone = true;

	}

	@Override
	public void finish() {
		if (undone) {
			img.destroy();
		}

	}

}
