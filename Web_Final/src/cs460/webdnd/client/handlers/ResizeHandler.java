package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.DragResizeMoveEvent;
import com.smartgwt.client.widgets.events.DragResizeMoveHandler;
import com.smartgwt.client.widgets.events.DragResizeStartEvent;
import com.smartgwt.client.widgets.events.DragResizeStartHandler;
import com.smartgwt.client.widgets.events.DragResizeStopEvent;
import com.smartgwt.client.widgets.events.DragResizeStopHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.ResizeCommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;

/***
 * This is the standard ResizeHandler to be utilized by the
 * <code>SelectionMarquee</code> Object. This handler handles resize start,
 * resize move, and resize stop handler functions to allow for complete
 * functionality.
 * 
 * Upon starting, this handler makes note of the initial values the selected
 * item has, and maintains them for future reference.
 * 
 * While moving the resize around, the handler updates the state in real-time,
 * so the user can see the visual impact of the change.
 * 
 * Upon ending, the Handler creates and Command to pass to the Model that can be
 * undone, so the changes can be undone, should the user want them to be.
 * 
 * @author Theodore Schnepper
 */
public class ResizeHandler implements DragResizeStartHandler, DragResizeStopHandler, DragResizeMoveHandler {
	private IController cont;
	private String startWidth;
	private String startHeight;
	private String startx;
	private String starty;
	private Canvas marquee;
	private Canvas item;

	public ResizeHandler(IController cont, Canvas marquee) {
		this.cont = cont;
		this.marquee = marquee;
	}

	@Override
	public void onDragResizeMove(DragResizeMoveEvent event) {
		this.item.setWidth(marquee.getWidth() - 4);
		this.item.setHeight(marquee.getHeight() - 4);
		this.item.setLeft(marquee.getLeft() + 2);
		this.item.setTop(marquee.getTop() + 2);
		// cont.getModel().updateViews();
	}

	@Override
	public void onDragResizeStop(DragResizeStopEvent event) {
		System.out.println("Top: " + starty + " " + item.getTop());
		System.out.println("Left: " + startx + " " + item.getLeft());
		cont.getModel().run(
				new ResizeCommand(item, startWidth, startHeight, startx,
						starty, item.getWidthAsString(), item
								.getHeightAsString(), item.getLeftAsString(),
						item.getTopAsString()));
	}

	@Override
	public void onDragResizeStart(DragResizeStartEvent event) {
		CState state = Misc.getCState(cont);
		if (state == null)
			return;

		Canvas item = state.getSelection();

		if (item == null)
			return;

		this.item = item;
		this.startWidth = item.getWidthAsString();
		this.startHeight = item.getHeightAsString();
		this.startx = item.getLeftAsString();
		this.starty = item.getTopAsString();
	}

}
