package cs460.webdnd.client.elements;

import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.BrowserEvent;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.MouseDownHandler;
import com.smartgwt.client.widgets.events.MouseUpEvent;
import com.smartgwt.client.widgets.events.MouseUpHandler;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.DeleteCommand;
import cs460.webdnd.client.handlers.MoveHandler;
import cs460.webdnd.client.handlers.ResizeHandler;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

/**
 * A view to illustrate what item we currently have selected, and are
 * manipulating. This is done by placing this 'Canvas' over the item, giveing it
 * the same dimensions and position as the canvas (with a little girth) and
 * adding a dashed border to show where the selection is.
 * 
 * @author Theodore Schnepper
 */
public class SelectionMarquee extends Canvas implements IView, IController {
	private IModel model;

	public SelectionMarquee(final IModel model) {
		this.setStyleName("Selection");
		this.setBorder("1px Dashed Black;");
		this.setVisible(false);
		this.model = model;
		
		ResizeHandler resizeHandle = new ResizeHandler(this, this);
		this.addDragResizeMoveHandler(resizeHandle);
		this.addDragResizeStartHandler(resizeHandle);
		this.addDragResizeStopHandler(resizeHandle);

		MoveHandler moveHandle = new MoveHandler(this, this);
		this.addDragRepositionMoveHandler(moveHandle);
		this.addDragRepositionStartHandler(moveHandle);
		this.addDragRepositionStopHandler(moveHandle);
		this.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				Canvas c = Misc.getCState(model).getSelection();
				if (c == null)
					return;

				c.fireEvent(event);
			}
		});

		/**
		 * @author niallschavez This allows users to delete items from the page
		 *         :)
		 */

		final CState state = Misc.getCState(model);
		state.getMainCanvas().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				// SC.say("you pressed "+event.getKeyName());
				if (event.getKeyName().equalsIgnoreCase("Escape")) {
					if (state.getSelection() == null)
						return;
					model.run(new DeleteCommand(model));
				}
			}
		});

		/**
		 * End @author niallschavez
		 */

		mHandler mh = new mHandler(this);
		this.addMouseDownHandler(mh);
		this.addMouseUpHandler(mh);
		this.setKeepInParentRect(true);

		this.setDragAppearance(DragAppearance.TARGET);
		model.addView(this);
	}

	@Override
	public void Update(IState state) {
		if (!(state instanceof CState))
			return;

		CState cstate = (CState) state;
		Canvas sel = cstate.getSelection();

		if (sel != null) {
			this.setWidth(sel.getWidth() + 4);
			this.setHeight(sel.getHeight() + 4);
			if (sel.getParentElement() != null) {
				this.setLeft(sel.getAbsoluteLeft()
						- sel.getParentElement().getAbsoluteLeft() - 2);
				this.setTop(sel.getAbsoluteTop()
						- sel.getParentElement().getAbsoluteTop() - 2);
				sel.getParentElement().addChild(this);
			} else {
				sel.addChild(this);
			}
			this.setVisible(true);
			this.moveAbove(sel);

			if (!isPageElement(sel)) {
				this.setCanDragResize(true);
				this.setCanDragReposition(true);
				this.setResizeFrom((String[]) null);
			} else {
				this.setCanDragReposition(false);
				this.setCanDragResize(true);
				this.setResizeFrom(new String[] { "B" });
			}

		} else {
			this.setVisible(false);
			this.setCanDragResize(false);
			this.setCanDragReposition(false);
		}
	}

	private boolean isPageElement(Canvas c) {
		CState state = Misc.getCState(model);
		if (state == null)
			return false;
		for (WebPage p : state.getPages()) {
			if (p.getHeader() == c || p.getBody() == c || p.getFooter() == c)
				return true;
		}
		
		final WebPage template = state.getTemplate();
		if(template == null)
			return false;
		if (template.getHeader() == c
				|| template.getBody() == c
				|| template.getFooter() == c)
			return true;

		return false;
	}

	@Override
	public void registerModel(IModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}
}

/***
 * This mouse Handler is used to resolve any mouse events that might occur in
 * the SelectionMarquee, but are actually intended for Children of the Selected
 * Item.
 * 
 * @author Theodore Schnepper
 * 
 */
class mHandler implements MouseDownHandler, MouseUpHandler {

	private IController cont;

	public mHandler(IController cont) {
		this.cont = cont;
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		CState state = Misc.getCState(cont);
		if (state == null)
			return;

		Canvas c = state.getSelection();
		if (c == null)
			return;

		c = getApplicableChild(event, c);
		if (c == null)
			return;
		c.fireEvent(event);

	}

	private Canvas getApplicableChild(BrowserEvent<?> event, Canvas c) {
		Canvas result = null;

		if (isWithin(c, event.getX(), event.getY())) {
			for (Canvas c1 : c.getChildren()) {
				result = getApplicableChild(event, c1);
			}
			if (result == null)
				result = c;
		}
		return result;
	}

	private static boolean isWithin(Canvas c, int x, int y) {
		if (c.getAbsoluteLeft() <= x && c.getAbsoluteTop() <= y) {
			if (c.getAbsoluteLeft() + c.getWidth() >= x && c.getAbsoluteTop() + c.getHeight() >= y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		CState state = Misc.getCState(cont);
		if (state == null)
			return;

		Canvas c = state.getSelection();
		if (c == null)
			return;

		c = getApplicableChild(event, c);
		if (c == null)
			return;
		c.fireEvent(event);
	}
}