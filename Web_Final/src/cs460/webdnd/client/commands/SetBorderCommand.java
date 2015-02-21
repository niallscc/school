package cs460.webdnd.client.commands;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/**
 * Sets the border style of a canvas. This command is undoable.
 * 
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
public class SetBorderCommand implements IUndoableCommand {
	private Canvas element;
	private String style;
	private String old_style;

	public SetBorderCommand(Canvas element, String style) {
		this.element = element;
		this.style = style;
		// this.old_style = old_style;
		this.old_style = element.getStyleName();
	}

	@Override
	public void run() {
		element.setStyleName(style);
	}

	@Override
	public void undo() {
		element.setStyleName(old_style);
	}

	@Override
	public void finish() {
		return;
	}
}