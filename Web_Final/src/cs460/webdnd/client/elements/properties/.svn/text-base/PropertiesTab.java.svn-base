/**
 * This is the properties panel 
 * @author niallschavez
 * @author JohnSchulz
 */
package cs460.webdnd.client.elements.properties;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;

import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.commands.editor.SetPropertiesCommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;

/**
 * A now deprecated Tab for modifying the properties of a canvas, including
 * title, x/y position, width/height, background color, z-index, visibility, and
 * whether it is enabled or not.
 * 
 * @deprecated
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
@Deprecated
public class PropertiesTab extends Tab implements IController, IView {
	protected TextItem title;
	protected TextItem posx;
	protected TextItem posy;
	protected TextItem width;
	protected TextItem height;
	protected ColorPickerItem bgcolor;
	protected TextItem zindex;
	protected CheckboxItem visible;
	protected CheckboxItem disable;
	private IButton submit;
	// private Canvas thisCanvas;
	private IModel model;
	private Boolean created = false;

	public PropertiesTab(final IModel model) {
		this.model = model;

		Canvas thisCanvas = new Canvas();
		DynamicForm form = new DynamicForm();
		this.setTitle("Properties");

		// thisCanvas.setSize("280px", "365px");
		// thisCanvas.setPosition(Positioning.RELATIVE);
		// thisCanvas.setLeft("1050px");
		// thisCanvas.setTop("25px");
		// thisCanvas.setDragAppearance(DragAppearance.TARGET);
		// thisCanvas.setBorder("1px black");
		// thisCanvas.setBackgroundColor("#B8B8B8");
		VStack layout = new VStack();
		layout.setWidth(220);

		// Title Property
		title = new TextItem();
		title.setTitle("Title: ");
		title.disable();

		// X Position Property
		posx = new TextItem();
		posx.setTitle("X Position: ");
		posx.disable();

		// Y Position Property
		posy = new TextItem();
		posy.setTitle("Y Position: ");
		posy.disable();

		// Width Property
		width = new TextItem();
		width.setTitle("Width: ");
		width.disable();

		// Height Property
		height = new TextItem();
		height.setTitle("Height: ");
		height.disable();

		// Background Color Property
		bgcolor = new ColorPickerItem();
		bgcolor.setTitle("BG Color: ");
		bgcolor.disable();

		// Opacity Property

		// Drag Opacity Property

		// Z Index Property
		zindex = new TextItem();
		zindex.setTitle("Z Index: ");
		zindex.disable();

		// Visibility Property
		visible = new CheckboxItem();
		visible.setTitle("Visible: ");
		visible.disable();

		// Disabled Property
		disable = new CheckboxItem();
		disable.setTitle("Disable: ");
		disable.disable();

		// Submit button
		submit = new IButton("Submit");
		submit.setLayoutAlign(Alignment.CENTER);
		submit.disable();
		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (model.getState() instanceof CState) {
					CState state = (CState) model.getState();
					model.run(new SetPropertiesCommand(state.getSelection(),
							title.getValueAsString(), Integer.valueOf(posx
									.getValueAsString()), Integer.valueOf(posy
									.getValueAsString()), width
									.getValueAsString(), height
									.getValueAsString(), bgcolor
									.getValueAsString(), Integer.valueOf(zindex
									.getValueAsString()), visible
									.getValueAsBoolean(), disable
									.getValueAsBoolean()));
				}
			}
		});
		// propertiesWin.addItem(submit);
		form.setFields(new FormItem[] { title, posx, posy, width, height,
				bgcolor, zindex, visible, disable });

		layout.addMember(form);
		layout.addMember(submit);

		// thisCanvas.setSize("280px", "365px");

		thisCanvas.setCanDragReposition(true);
		thisCanvas.setCanDrag(true);
		this.setPane(layout);
		// thisCanvas.draw();
	}

	/**
	 * Update sets the values of the form items corresponding to the attributes
	 * of a canvas, depending on whether a canvas is selected or not.
	 */
	@Override
	public void Update(IState state) {
		// TODO Auto-generated method stub
		if (!(state instanceof CState))
			return;

		CState cstate = (CState) state;
		Canvas cIn = cstate.getSelection();
		if (cIn == null) {
			title.setValue("");
			title.disable();
			posx.setValue("");
			posx.disable();
			posy.setValue("");
			posy.disable();
			width.setValue("");
			width.disable();
			height.setValue("");
			height.disable();
			bgcolor.setValue("#000000");
			bgcolor.disable();
			visible.setValue(false);
			visible.disable();
			zindex.setValue(0);
			zindex.disable();
			disable.setValue(false);
			disable.disable();
		} else {
			title.setValue(cIn.getTitle());
			title.enable();
			posx.setValue(cIn.getLeft());
			posx.enable();
			posy.setValue(cIn.getTop());
			posy.enable();
			width.setValue(cIn.getWidth());
			width.enable();
			height.setValue(cIn.getHeight());
			height.enable();
			bgcolor.setValue(cIn.getBackgroundColor());
			bgcolor.enable();
			visible.setValue(cIn.isVisible());
			visible.enable();
			disable.setValue(cIn.isDisabled());
			disable.enable();
			zindex.setValue(cIn.getZIndex());
			zindex.enable();
			submit.enable();
		}
	}

	@Override
	public void registerModel(IModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	/**
	 * Returns whether a command has been isused that created this cavnas
	 */
	@Override
	public boolean isCreated() {
		return created;
	}

	/**
	 * Says that a command has been issued and that this canvas has been created
	 */
	public void doCreate() {
		created = true;
	}

}
