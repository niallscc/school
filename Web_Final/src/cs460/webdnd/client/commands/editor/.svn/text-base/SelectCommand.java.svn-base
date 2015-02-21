package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;

/**
 * This Command updates the Selection to the specified Canvas within the State.
 * 
 * @author Theodore Schnepper
 */
public class SelectCommand implements ICommand {
	private CState state;
	private Canvas item;

	/***
	 * This Creates the SelectCommand. This command needs the State, so it can
	 * modify it, as well as the Canvas it will be changing the selection to.
	 * 
	 * @param state
	 *            The State to be modified
	 * @param selection
	 *            The Canvas to make the current selection
	 */
	public SelectCommand(CState state, Canvas selection) {
		this.item = selection;
		this.state = state;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (item == null) {
			state.setSelection(state.getMainCanvas());
			state.update();
		}
		state.setSelection(item);
	}
}