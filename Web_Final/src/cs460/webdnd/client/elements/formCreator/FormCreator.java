/**
 * this is the Form creator, lets you create a form by dragging from a menu into a list grid
 * which then creates the form
 * @author niallschavez
 */
package cs460.webdnd.client.elements.formCreator;

import java.util.LinkedList;

//import com.google.gwt.core.client.EntryPoint;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.controller.IController;

public class FormCreator {

	protected static LinkedList<FormItem> formItems;
	protected DragForm form;
	FormElementData d;
	IController cont;
	Canvas formCreator = new Canvas();
	Canvas createdForm = new Canvas();

	public FormCreator(IController cont) {
		this.cont = cont;
		form = new DragForm();
		formItems = new LinkedList<FormItem>();
		d = new FormElementData();

		create();
	}

	public void create() {

		ListGridField elementNameField = new ListGridField("elementName");
		elementNameField.setWidth(70);

		final Menu menu = new Menu();
		menu.setFields(elementNameField);
		menu.setData(d.getRecords());
		menu.setSelectionType(SelectionStyle.SINGLE);
		menu.setCanDragRecordsOut(true);
		menu.setDragDataAction(DragDataAction.COPY);

		IMenuButton menuButton = new IMenuButton();
		menuButton.setTitle("Elements");
		menuButton.setMenu(menu);

		final ListGrid partsGrid = new ListGrid();
		partsGrid.setWidth(300);
		partsGrid.setCanAcceptDroppedRecords(true);
		partsGrid.setCanReorderFields(true);

		ListGridField partNameField2 = new ListGridField("elementName");
		partsGrid.setFields(partNameField2);
		partsGrid.setHeight("150");
		partsGrid.addDropHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {

				final Record[] r = menu.getDragData();

				final String title = r[0].getAttributeAsString("elementName");
				SC.askforValue("New Title", "Please enter a title for your "
						+ title, new ValueCallback() {
					@Override
					public void execute(String value) {

						Object f = r[0].getAttributeAsObject("type");
						partsGrid.removeData(r[0]);
						partsGrid.removeData(r[0]);
						partsGrid.addData(d.addRecord(value, f));
						FormElementFactory fef = new FormElementFactory(title);
						FormItem newItem = fef.getFormElement(value);
						new addFormElement(newItem);
					}
				});
			}
		});

		final HLayout formCreatorLayout = new HLayout(90);
		formCreatorLayout.setCanDragReposition(true);
		formCreatorLayout.setBorder("solid");
		formCreatorLayout.setSize("500", "200");
		formCreatorLayout.setTop(400);
		formCreatorLayout.setLeft("30%");
		formCreatorLayout.setBackgroundColor("#B8B8B8");
		formCreatorLayout.setDragAppearance(DragAppearance.TARGET);

		Button submit = new Button("Create Form");
		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				createdForm.setCanDragReposition(true);
				createdForm.setBorder("solid");
				createdForm.setCanDragResize(true);
				createdForm.setKeepInParentRect(true);
				createdForm.setDragAppearance(DragAppearance.TARGET);

				FormItem[] f = new FormItem[formItems.size()];
				for (int i = 0; i < formItems.size(); i++) {
					f[i] = formItems.get(i);
				}

				form.setStyleName("drag");
				form.setWidth(250);
				form.setFields(f);

				formCreator.setVisibility(Visibility.HIDDEN);

				createdForm.addChild(form);
				createdForm.setVisibility(Visibility.VISIBLE);
				// createdForm. setBackgroundColor("#B8B8B8");

			}
		});

		Button remove = new Button("Remove Selected");
		remove.setWidth(120);
		remove.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (partsGrid.anySelected()) {
					formItems.remove(partsGrid.getSelectedRecord());
					partsGrid.removeData(partsGrid.getSelectedRecord());

				}
			}
		});
		HLayout actions = new HLayout(5);
		actions.setMembers(submit, remove);

		VLayout partsAndActions = new VLayout();
		partsAndActions.setMembers(partsGrid, actions);
		formCreatorLayout.setMembers(menuButton, partsAndActions);

		formCreator.addChild(formCreatorLayout);
		formCreator.setVisibility(Visibility.VISIBLE);
		formCreator.addMouseDownHandler(SelectOnClickHandler
				.getSelectionHandler(cont));

	}

	/*
	 * This class is a work around because I was having object copying issues...
	 */
	private class addFormElement {

		public addFormElement(FormItem in) {
			System.out.println("The copied data is: " + in.getTitle()
					+ "location: " + in);
			formItems.add(in);
		}
	}

	public static class DragForm extends DynamicForm {

		public DragForm() {

			setAlign(Alignment.CENTER);
			setPadding(4);
			setShowEdges(true);
			setMinWidth(70);
			setMinHeight(70);
			setMaxWidth(300);
			setMaxHeight(200);
			setKeepInParentRect(true);
		}
	}

	public Canvas getFormCanvas() {
		return createdForm;
	}

	public Canvas getFormCreator() {
		return formCreator;
	}
}