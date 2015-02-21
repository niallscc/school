package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.elements.menuStyles.MenuStylePicker;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/**
 * This command allows users to add different menu styles to their menus, pretty awesome stuff
 * if you ask me :P
 * 
 * @author niallschavez
 */
public class MenuStyleCommand implements IUndoableCommand {
	IController cont;
	Canvas parent;
	Canvas menuPicker;
	Canvas createdMenu;
	Canvas mainCanvas;
	boolean undone = false;

	public MenuStyleCommand(IController cont, Canvas parent, Canvas mainCanvas) {
		this.cont = cont;
		this.parent = parent;
		this.undone = false;
		this.mainCanvas = mainCanvas;
	}

	@Override
	public void run() {
		this.undone = false;
		MenuStylePicker msp = new MenuStylePicker(cont);
		menuPicker = msp.getCanvas();
		createdMenu = msp.getMenu();
		mainCanvas.addChild(menuPicker);
		parent.addChild(createdMenu);
		parent.redraw();
	}

	@Override
	public void undo() {
		menuPicker.setVisibility(Visibility.HIDDEN);
		createdMenu.setVisibility(Visibility.HIDDEN);

	}

	@Override
	public void finish() {
		menuPicker.destroy();
	}
}
