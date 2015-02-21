/**
 * 
 */
package cs460.webdnd.client.commands.editor;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.elements.addMedia.AddMedia;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;
import cs460.webdnd.client.mvc.model.IModel;

/**
 * This command creates a canvas in which people will be
 * able to copy the html of the media that they want to display on their
 * page and when they click submit, it will create a canvas in their
 * selection that contains that media.
 * 
 * @author niallschavez 
 */
public class MediaCommand implements IUndoableCommand {
	IModel model;
	Canvas mediaCanvas;
	boolean undone;
	
	public MediaCommand(IModel model) {
		this.model = model;
		undone=false;
	}

	@Override
	public void run() {
		AddMedia am= new AddMedia(model);
		mediaCanvas= am.getMediaCanvas();
	}

	@Override
	public void undo() {
		undone=true;

	}

	@Override
	public void finish() {
		mediaCanvas.destroy();
	}

}
