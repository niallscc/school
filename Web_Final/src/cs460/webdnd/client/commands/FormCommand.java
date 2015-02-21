package cs460.webdnd.client.commands;

/**
 * @author niallschavez
 */
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.elements.formCreator.FormCreator;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/**
 * Well, we aren't using this anymore, but it's still here if we ever end up
 * being awesome and getting form creation working :P
 * 
 * @author niallschavez
 */
public class FormCommand implements IUndoableCommand {
	private IController cont;
	private Canvas parent;
	private Canvas createdForm;
	private Canvas formCreator;
	private Canvas mainCanvas;

	public FormCommand(IController cont, Canvas parent, Canvas mainCanvas) {
		this.mainCanvas = mainCanvas;
		this.cont = cont;
		this.parent = parent;
	}

	@Override
	public void run() {
		FormCreator fc = new FormCreator(cont);
		createdForm = fc.getFormCanvas();
		formCreator = fc.getFormCreator();

		mainCanvas.addChild(formCreator);
		parent.addChild(createdForm);
	}

	@Override
	public void undo() {
		formCreator.setVisibility(Visibility.HIDDEN);
		createdForm.setVisibility(Visibility.HIDDEN);

	}

	@Override
	public void finish() {
		createdForm.destroy();
		formCreator.destroy();

	}
}
