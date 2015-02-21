/**
 * 
 */
package cs460.webdnd.client.commands.editor;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.servercommunication.DeleteFileCommand;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

/**
 * Allows users to rename tabs
 * @author niallschavez
 */
public class RenameTabCommand implements ICommand {
	IModel model;
	TabSet topTabSet;
	Canvas mainCanvas;

	/**
	 * 
	 * @param topTabSet
	 *            the tab set that we are currently displaying
	 * @param mainCanvas
	 *            the main canvas of the project, this is so we can add the
	 *            window in to the main canvas
	 */
	public RenameTabCommand(IModel model, Canvas mainCanvas) {
		this.model = model;
		CState state = Misc.getCState(model);
		
		if(state != null){
			this.topTabSet = state.getMenuEditor().getTabSet();
			this.mainCanvas = mainCanvas;
		}
	}

	@Override
	public void run() {
		if (topTabSet.getSelectedTab().equals(topTabSet.getTab("template")) ||
			topTabSet.getSelectedTab().equals(topTabSet.getTabs()[topTabSet.getTabs().length-1]) ||
			topTabSet.getSelectedTab().equals(topTabSet.getTab("index")))
		{
			SC.say("Cannot Rename the \""+topTabSet.getSelectedTab().getID()+"\" tab");
		} else {
			final Tab temp = topTabSet.getSelectedTab();
			
			final Canvas editTabNameCanvas = new Canvas();
			editTabNameCanvas.setTitle("Rename tab");
			editTabNameCanvas.setHeight("200px");
			editTabNameCanvas.setWidth("200px");
			editTabNameCanvas.setBackgroundColor("#B8B8B8");
			editTabNameCanvas.setBorder("1px solid");
			editTabNameCanvas.setCanDragReposition(true);
			editTabNameCanvas.setTop(topTabSet.getTop() + 30);
			editTabNameCanvas.setLeft(topTabSet.getLeft() + 40);

			final TextBox setTabNameBox = new TextBox();
			setTabNameBox.setValue(temp.getTitle());

			Button submitButton = new Button();
			submitButton.setText("submit");
			submitButton.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
				@Override
				public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
					String name = temp.getTitle();
					topTabSet.setTabTitle(topTabSet.getSelectedTabNumber(), setTabNameBox.getValue());
					temp.setTitle(setTabNameBox.getValue());
					((WebPage)temp.getPane()).setName(setTabNameBox.getValue());
					editTabNameCanvas.destroy();
					model.run(new DeleteFileCommand(name + ".bin", model));
				}
			});
			
			Button cancel = new Button();
			cancel.setText("Cancel");
			cancel.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
				@Override
				public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
					editTabNameCanvas.destroy();
				}
			});
			
			HLayout buttons = new HLayout(10);
			buttons.addMember(submitButton);
			buttons.addMember(cancel);

			VLayout layout = new VLayout(10);
			layout.addMember(setTabNameBox);
			layout.addMember(buttons);

			editTabNameCanvas.addChild(layout);
			mainCanvas.addChild(editTabNameCanvas);
		}
	}

}
