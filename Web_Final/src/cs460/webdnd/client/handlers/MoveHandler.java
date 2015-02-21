package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.DragRepositionMoveEvent;
import com.smartgwt.client.widgets.events.DragRepositionMoveHandler;
import com.smartgwt.client.widgets.events.DragRepositionStartEvent;
import com.smartgwt.client.widgets.events.DragRepositionStartHandler;
import com.smartgwt.client.widgets.events.DragRepositionStopEvent;
import com.smartgwt.client.widgets.events.DragRepositionStopHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.MoveCommand;
import cs460.webdnd.client.mvc.controller.IController;

/**
 * This is a Custom Move Handler for the SelectionMarquee that keeps track of
 * when a Drag Starts, Moves, and Stops. It grabs the initial selection's x and
 * y coordinates at the start to pass for the MoveCommand. As the Drag is moved,
 * the selection's position is updated so the user has visual feedback about the
 * move. When the Drag ends, it grabs the ending coordinates for the selection
 * and passes them to a new MoveCommand along with the original values so the
 * Move can be undone if the user would like.
 * 
 * @author Theodore Schnepper
 */
public class MoveHandler implements DragRepositionStartHandler, DragRepositionMoveHandler, DragRepositionStopHandler {
	private Canvas marquee;
	private IController cont;
	private String oldX;
	private String oldY;
	private Canvas item;

	public MoveHandler(IController cont, Canvas marquee) {
		this.cont = cont;
		this.marquee = marquee;
	}

	@Override
	public void onDragRepositionStop(DragRepositionStopEvent event) {
		if (item == null)
			return;

		cont.getModel().run(
				new MoveCommand(item, oldX, oldY, item.getLeftAsString(), item
						.getTopAsString()));

	}

	@Override
	public void onDragRepositionMove(DragRepositionMoveEvent event) {
		if (item == null)
			return;
		item.setLeft(marquee.getLeft() + 2);
		item.setTop(marquee.getTop() + 2);
		// cont.getModel().updateViews();
	}

	@Override
	public void onDragRepositionStart(DragRepositionStartEvent event) {
		if (!(cont.getModel().getState() instanceof CState))
			return;
		CState state = (CState) cont.getModel().getState();
		Canvas item = state.getSelection();

		if (item == null)
			return;

		this.item = item;
		this.oldX = item.getLeftAsString();
		this.oldY = item.getTopAsString();
	}
}