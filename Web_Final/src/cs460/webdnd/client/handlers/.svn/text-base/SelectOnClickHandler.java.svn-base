package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.events.MouseMoveEvent;
import com.smartgwt.client.widgets.events.MouseMoveHandler;
import com.smartgwt.client.widgets.events.MouseUpEvent;
import com.smartgwt.client.widgets.events.MouseUpHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.SelectCommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;

/***
 * This class contains the SelectOnClickHandler, as well as a static function to
 * retrieve one SelectOnClickHandler. This makes this, essentially, a Singleton
 * class. The reason for this is to resolve click conflicts when attempting to
 * select items. When click multiple item's Click Events are Triggered. As all
 * of those items would resolve to one MouseHandler, it allows for us to
 * consider each of them and only keep the Items that is the farthest down in
 * the Heirarchy. This will ensure that the 'top' item is always selected.
 * 
 * @author Theodore Schnepper
 * 
 */
public class SelectOnClickHandler implements MouseDownHandler, MouseUpHandler, MouseMoveHandler {
	private IController cont;
	private Canvas currentSelection;
	private static SelectOnClickHandler theDefault;

	private SelectOnClickHandler(IController cont, Canvas item) {
		this.cont = cont;
		// this.item = item;
	}

	public static void giveSelectHandler(Canvas item) {
		item.addMouseDownHandler(theDefault);
		item.addMouseUpHandler(theDefault);
		item.addMouseMoveHandler(theDefault);
	}

	public static SelectOnClickHandler getSelectionHandler(IController cont) {
		if (theDefault == null)
			theDefault = new SelectOnClickHandler(cont, null);

		return theDefault;
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		CState state = Misc.getCState(cont);
		if (state == null)
			return;

		if (currentSelection == null)
			return;

		cont.getModel().run(new SelectCommand(state, currentSelection));
		currentSelection = null;
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (event.getSource() == null)
			return;
		if (currentSelection == null)
			currentSelection = (Canvas) event.getSource();
		else {
			Canvas c = (Canvas) event.getSource();
			if (isAncestor(c, currentSelection) /*
												 * || c.getZIndex() >
												 * currentSelection.getZIndex()
												 */)
				currentSelection = c;
		}
	}

	private boolean isAncestor(Canvas child, Canvas ancestor) {
		if (child == null)
			return false;

		if (child.getParent() == ancestor)
			return true;

		return isAncestor(child.getParentElement(), ancestor);
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		// we Moved?
		currentSelection = null;
	}
}