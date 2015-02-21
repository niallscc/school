package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.CanvasHideCommand;
import cs460.webdnd.client.commands.CanvasShowCommand;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

public class DeleteCommand implements IUndoableCommand {
	IModel model;
	Canvas selection;
	boolean undone;

	public DeleteCommand(IModel model) {
		CState state = Misc.getCState(model);

		if (state == null)
			return;

		selection = state.getSelection();

		if (selection == null)
			return;

		undone = false;
		this.model = model;
	}

	@Override
	public void run() {
		if (!undone) {
			SC.confirm("Are you sure you want to delete this item?",
					new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value){
								model.run(new CanvasHideCommand(selection));
								selection.disable();
								selection.setDisabled(true);
								
								model.run(new SelectCommand(Misc.getCState(model),null));
								undone=false;
							}
						}
					});
		} else{
			model.run(new CanvasHideCommand(selection));
			model.run(new SelectCommand(Misc.getCState(model),null));
		}
	}

	@Override
	public void undo() {
		undone=true;
		model.run(new CanvasShowCommand(selection));
		selection.enable();
		model.run(new SelectCommand(Misc.getCState(model), selection));

	}

	@Override
	public void finish() {
		if (!undone && selection != null)
			selection.destroy();

	}

}
