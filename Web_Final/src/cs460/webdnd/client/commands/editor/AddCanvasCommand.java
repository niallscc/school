package cs460.webdnd.client.commands.editor;

import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;
import cs460.webdnd.client.utilities.Misc;

/***
 * This is a Command to add a new canvas to the currently selected Canvas. Then,
 * it will select the newly added Canvas.
 * 
 * @author Theodore Schnepper
 * 
 */
public class AddCanvasCommand implements IUndoableCommand {

	private IController cont;
	private Canvas parent;
	private Canvas newItem;
	private boolean undone;

	/***
	 * This specifies the information needed to create a Canvas. We keep the
	 * parent to maintain a hierarchy. We don't get the selection as it is
	 * variable, and if this command is to be undone and redone, this variable
	 * has to be static.
	 * 
	 * @param cont
	 *            an IController to help change the current Selection.
	 * @param parent
	 *            The parent Canvas to create the new Canvas within.
	 */
	public AddCanvasCommand(IController cont, Canvas parent) {
		this.cont = cont;
		this.parent = parent;
		this.undone = false;
	}

	@Override
	public void run() {
		this.undone = false;
		if (newItem == null) {
			newItem = new Canvas();
			parent.addChild(newItem);
			// newItem.addMouseDownHandler(SelectOnClickHandler.getSelectionHandler(cont));
			// newItem.addMouseUpHandler(SelectOnClickHandler.getSelectionHandler(cont));
			SelectOnClickHandler.giveSelectHandler(newItem);
			parent.addChild(newItem);
		} else {
			newItem.setVisible(true);
		}
		if (!(cont.getModel().getState() instanceof CState))
			return;
		CState state = (CState) cont.getModel().getState();
		state.setSelection(newItem);

	}

	@Override
	public void undo() {
		newItem.setVisible(false);
		this.undone = true;

		CState state = Misc.getCState(cont);
		if (state == null)
			return;

		state.setSelection(parent);
	}

	@Override
	public void finish() {
		if (undone)
			newItem.destroy();
	}

}
