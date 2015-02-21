/**
 * @author niallschavez
 * this class is the main canvas that allows users to 
 * pick their menu style :) 
 */
package cs460.webdnd.client.elements.menuStyles;

import java.util.LinkedList;
/**
 * @author niallschavez
 */
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.mvc.controller.IController;

public class MenuStylePicker {

	protected static LinkedList<FormItem> formItems;

	protected IController cont;
	protected Canvas menuStyleCanvas;
	protected Tab[] tabs;
	protected Canvas menuCanvas = new Canvas();

	public MenuStylePicker(IController cont) {
		this.cont = cont;

		menuStyleCanvas = new Canvas();
		formItems = new LinkedList<FormItem>();
		CState s = (CState) cont.getModel().getState();
		tabs = s.getMenuEditor().getTabSet().getTabs();
		create();
	}

	public void create() {
		final ListGrid menuGrid = new ListGrid();
		menuCanvas.setVisibility(Visibility.HIDDEN);
		menuGrid.setWidth(560);
		menuGrid.setCanReorderFields(true);
		menuGrid.setHeight("150%");

		ListGridField nameField = new ListGridField("name", "Name");
		ListGridField schemeField = new ListGridField("scheme", "Color Scheme");
		ListGridField creatorField = new ListGridField("creator", "Creator");
		ListGridField dateField = new ListGridField("date", "Date");

		menuGrid.setFields(/* preview, */nameField, schemeField, creatorField, dateField);
		menuGrid.setData(MenuData.getRecords());

		Button submit = new Button("Create Menu");
		Button cancel = new Button("Cancel");
		final VLayout formCreatorLayout = new VLayout(5);

		final HLayout buttonLayout = new HLayout(10);
		buttonLayout.addMember(submit);
		buttonLayout.addMember(cancel);

		formCreatorLayout.setCanDragReposition(true);
		formCreatorLayout.setBorder("solid");
		formCreatorLayout.setSize("500", "220");
		formCreatorLayout.setTop(400);
		formCreatorLayout.setLeft("30%");
		formCreatorLayout.setBackgroundColor("#B8B8B8");
		formCreatorLayout.setDragAppearance(DragAppearance.TARGET);
		formCreatorLayout.addMember(menuGrid);
		formCreatorLayout.addMember(buttonLayout);
		menuStyleCanvas.addChild(formCreatorLayout);

		/***********************
		 * Submit button stuff *
		 ***********************/
		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final ListGridRecord selected = menuGrid.getSelectedRecord();

				String style = selected.getAttributeAsString("styleSheet");
				// MyMenuBar menu = new MyMenuBar(cont);
				NavMenu menu = new NavMenu(style, cont);
				Canvas navMenu = menu;
				navMenu.setWidth("100%");

				menuCanvas.addChild(navMenu);
				menuCanvas.setWidth100();
				menuCanvas.setCanDragReposition(true);
				menuCanvas.setVisibility(Visibility.VISIBLE);
				menuStyleCanvas.setVisibility(Visibility.HIDDEN);

			}
		});
		/***********************
		 * Cancel button stuff *
		 ***********************/

		cancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				menuStyleCanvas.setVisibility(Visibility.HIDDEN);
			}
		});
	}

	public Canvas getCanvas() {
		return menuStyleCanvas;
	}

	public Canvas getMenu() {
		return menuCanvas;
	}
}
