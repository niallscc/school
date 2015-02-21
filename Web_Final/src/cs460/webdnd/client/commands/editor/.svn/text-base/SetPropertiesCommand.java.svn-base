package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/***
 * This is a deprecated Command that is hanging around for no real reason. It
 * was originally used to handle all the changes on the properties window.
 * However, that window is no longer in use. This command initially retrieved
 * the old values of the current selection, and stored them so they could be
 * undone if the user wished. It then pulled the information from the properties
 * window, and set those values as the current values of the selection.
 * 
 * This command doesn't check for errors which could of lead to a lot of
 * potential issues, and if it were to be re-utilized it would need heavy
 * revisions.
 * 
 * @author Theodore Schnepper
 * 
 */
@Deprecated
public class SetPropertiesCommand implements IUndoableCommand {
	private Canvas element;
	private String newTitle;
	private int newPosx;
	private int newPosy;
	private String newWidth;
	private String newHeight;
	private String newBGColor;
	private int newZIndex;
	private boolean newVisible;
	private boolean newDisable;

	private String oldTitle;
	private int oldPosx;
	private int oldPosy;
	private String oldWidth;
	private String oldHeight;
	private String oldBGColor;
	private int oldZIndex;
	private boolean oldVisible;
	private boolean oldDisable;

	public SetPropertiesCommand(Canvas element, String title, int posx,
			int posy, String width, String height, String bgColor, int zindex,
			boolean visible, boolean disable) {
		this.element = element;
		this.newTitle = title;
		this.newPosx = posx;
		this.newPosy = posy;
		this.newWidth = width;
		this.newHeight = height;
		this.newBGColor = bgColor;
		this.newZIndex = zindex;
		this.newVisible = visible;
		this.newDisable = disable;
		/**
		 * Added this line in because it is constantly throwing errors
		 * 
		 * @author niallschavez
		 */
		if (element != null)
			this.oldTitle = element.getTitle();
		else
			this.oldTitle = "";

		this.oldPosx = element.getLeft();
		this.oldPosy = element.getTop();
		this.oldWidth = element.getWidthAsString();
		this.oldHeight = element.getHeightAsString();
		this.oldBGColor = element.getBackgroundColor();
		this.oldZIndex = element.getZIndex();
		this.oldVisible = element.isVisible();
		this.oldDisable = element.isDisabled();
	}

	@Override
	public void run() {
		element.setTitle(newTitle);
		element.setLeft(newPosx);
		element.setTop(newPosy);
		element.setWidth(newWidth);
		element.setHeight(newHeight);
		element.setBackgroundColor(newBGColor);
		element.setZIndex(newZIndex);
		element.setVisible(newVisible);
		element.setDisabled(newDisable);
	}

	@Override
	public void undo() {
		element.setTitle(oldTitle);
		element.setLeft(oldPosx);
		element.setTop(oldPosy);
		element.setWidth(oldWidth);
		element.setHeight(oldHeight);
		element.setBackgroundColor(oldBGColor);
		element.setZIndex(oldZIndex);
		element.setVisible(oldVisible);
		element.setDisabled(oldDisable);
	}

	@Override
	public void finish() {
		return;
	}
}