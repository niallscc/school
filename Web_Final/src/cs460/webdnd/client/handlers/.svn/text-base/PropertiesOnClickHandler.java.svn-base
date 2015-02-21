package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.SetPropertiesCommand;
import cs460.webdnd.client.mvc.controller.IController;

/***
 * This was the ClickHandler for the Original Properties Command. It took all
 * the values in the properties Window and passed them to the
 * SetPropertiesCommand.
 * 
 * It is no longer in use.
 * 
 * @author Theodore Schnepper
 * 
 */
@Deprecated
public class PropertiesOnClickHandler implements ClickHandler {

	private IController cont;
	private String title;
	private int posx;
	private int posy;
	private String width;
	private String height;
	private String bgcolor;
	private int zindex;
	private boolean visible;
	private boolean disable;

	@Deprecated
	public PropertiesOnClickHandler(IController cont, String title, int posx,
			int posy, String width, String height, String bgcolor, int zindex,
			boolean visible, boolean disable) {
		this.cont = cont;
		this.title = title;
		this.posx = posx;
		this.posy = posy;
		this.width = width;
		this.height = height;
		this.bgcolor = bgcolor;
		this.zindex = zindex;
		this.visible = visible;
		this.disable = disable;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (!(cont.getModel().getState() instanceof CState))
			return;
		CState state = (CState) cont.getModel().getState();
		if (state.getSelection() == null)
			return;
		cont.getModel()
				.run(new SetPropertiesCommand(state.getSelection(), title,
						posx, posy, width, height, bgcolor, zindex, visible,
						disable));
	}

}
