package cs460.webdnd.client.commands;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;

/**
 * Sets the background color or image of a canvas. This command is undoable.
 * 
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
public class SetBackgroundCommand implements IUndoableCommand {
	private Canvas element;
	private String newbgurl;
	private String newbgcol;

	private String oldbgurl;
	private String oldbgcol;

	public SetBackgroundCommand(Canvas element, String url, String color) {
			this.element= element;
			this.newbgurl = url;
			this.oldbgurl = element.getBackgroundImage();
			this.newbgcol = color;
			this.oldbgcol = element.getBackgroundColor();
	}

	@Override
	public void run() {
		//SC.say("setting background color to "+ newbgcol);
		if (newbgurl != null){
			element.setBackgroundImage(newbgurl.replaceFirst("thumb", "file"));
			//element.redraw();
		}
		else{
			element.setBackgroundImage("none");
			//element.redraw();
		}
		
		if(newbgcol == null || newbgcol.equals(""))
			element.setBackgroundColor("transparent");
		else
			element.setBackgroundColor(newbgcol);
	}

	@Override
	public void undo() {
		element.setBackgroundImage(oldbgurl);
		element.setBackgroundColor(oldbgcol);
	}

	@Override
	public void finish() {
		return;
	}

}
