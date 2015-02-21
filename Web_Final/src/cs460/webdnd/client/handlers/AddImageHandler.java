package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.AddImageCommand;
import cs460.webdnd.client.elements.ImgEx;
import cs460.webdnd.client.elements.images.ImageExplorer;
import cs460.webdnd.client.elements.images.ImageRepositioning;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;

/***
 * This is the AddImageHandler utilized solely by the Image Explorer. Upon
 * clicking the 'submit' button in the Image Explorer, this checks to see if
 * there has been an image Selected. If so, it will proceed to create a new
 * Image Canvas and callThe AddImageCommand to added it to the current
 * selection.
 * 
 * @author Theodore Schnepper
 */
public class AddImageHandler implements ClickHandler {
	private IController cont;
	private ImageExplorer ie;

	public AddImageHandler(IController cont, ImageExplorer ie) {
		this.cont = cont;
		this.ie = ie;
	}

	@Override
	public void onClick(ClickEvent event) {
		ImgEx img = (ImgEx) (ie.getSelection());
		if (img == null) {
			System.out.println("AddImageHandler.onClick { img == null }");
			return;
		}

		Canvas newImg = ImageRepositioning.imgItem(img.getPath(),
				img.getWidth(), img.getHeight(), 0, 0);

		CState state = Misc.getCState(cont);

		if (state == null)
			return;

		cont.getModel().run(new AddImageCommand(state.getSelection(), newImg));
	}
}