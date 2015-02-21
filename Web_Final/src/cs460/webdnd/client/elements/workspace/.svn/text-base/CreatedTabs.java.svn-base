/**
 * @author niallschavez
 * this is the main workspace where you put all your stuff 
 * contains the header body and footer
 */
package cs460.webdnd.client.elements.workspace;

import com.google.gwt.user.client.ui.Button;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.util.SC;

import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.editor.DeleteTabCommand;
import cs460.webdnd.client.commands.editor.NewTabCommand;
import cs460.webdnd.client.commands.editor.RenameTabCommand;
import cs460.webdnd.client.elements.ToolCanvas;
import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.handlers.AddTabChangedHandler;
import cs460.webdnd.client.handlers.MoveTemplateHandler;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

public class CreatedTabs extends ToolCanvas implements IView, IController {
	// private Canvas mainCanvas;
	private IModel model;
	TabSet topTabSet;

	public CreatedTabs(final Canvas mainCanvas, final IModel model) {
		this.model = model;

		topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);
		topTabSet.setPosition(Positioning.RELATIVE);
		topTabSet.setWidth100();
		topTabSet.setHeight("90%");
		topTabSet.setLeft(0);
		topTabSet.setTop(0);

		this.setTop(40);
		this.setLeft("5%");
		this.setHeight100();
		this.setWidth("75%");

		/********************
		 * Template tab stuff *
		 *********************/
		final Tab templateTab = new Tab("template");
		// topTabSet.setSelectedTab(0);
		templateTab.setID("template");
		final WebPage template = new WebPage("template");
		CState c = Misc.getCState(this);
		if (c != null)
			c.setTemplate(template);
		templateTab.setPane(template);

		topTabSet.addClickHandler(new AddTabChangedHandler(model, topTabSet));

		// final Canvas menu= new Canvas();
		// menu. setTop(topTabSet.getTop()+90);
		// menu. setLeft(topTabSet.getLeft()+ topTabSet.getTabs().length*70);
		// menu. setBackgroundColor("white");
		final Canvas menu = new Canvas();

		/***********************
		 * Delete Tab Stuff *
		 ************************/
		// ButtonItem close= new ButtonItem("delete tab");
		Button close = new Button("delete tab");
		close.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				getModel().run(new DeleteTabCommand(model));
				menu.setVisibility(Visibility.HIDDEN);

			}
		});
		/********************
		 * Rename tab stuff *
		 ********************/
		// ButtonItem rename = new ButtonItem("Rename tab");
		Button rename = new Button("Rename tab");
		rename.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				getModel().run(new RenameTabCommand(model, mainCanvas));
				menu.setVisibility(Visibility.HIDDEN);
			}
		});
		/***********************
		 * Cancel modifications
		 **********************/
		// ButtonItem cancelMenu= new ButtonItem("Cancel");
		Button cancelMenu = new Button("Cancel");
		cancelMenu
				.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
					@Override
					public void onClick(
							com.google.gwt.event.dom.client.ClickEvent event) {
						menu.setVisibility(Visibility.HIDDEN);
					}
				});
		HStack menuStack = new HStack();
		menuStack.addMember(close);
		menuStack.addMember(rename);
		menuStack.addMember(cancelMenu);
		menu.addChild(menuStack);

		// final DynamicForm form = new DynamicForm();
		// form. setGroupTitle("Add Tab");
		// form. setIsGroup(true);
		// form. setWidth(300);
		// form. setHeight(120);
		// form. setNumCols(2);
		// form. setColWidths(60, "*");
		// form. setPadding(5);
		// form. setCanDragResize(true);
		// form. setResizeFrom("R");
		// form. setFields(rename, close, cancelMenu);
		//
		// menu. addChild(form);
		menu.setVisibility(Visibility.HIDDEN);
		mainCanvas.addChild(menu);
		topTabSet.setContextMenu(null);
		topTabSet.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {

				if (event.getY() < 120) {
					menu.setTop(event.getY());
					menu.setLeft(event.getX());
					menu.setVisibility(Visibility.VISIBLE);
				}
			}
		});

		/********************
		 * Index tab stuff *
		 *********************/
		final Tab indexTab = new Tab("index");

		indexTab.setCanEditTitle(true);
		indexTab.setID("index");

		/********************
		 * add tab stuff *
		 ********************/
		final Tab addTab = new Tab("New Tab");
		addTab.setID("addTab");
		addTab.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				CState s = (CState) model.getState();
				s.setCurrentPane(addTab.getPane());
			}
		});

		WebPage index = new WebPage("index");
		Misc.getCState(getModel()).getPages().add(index);

		MoveTemplateHandler temp = new MoveTemplateHandler(index,
				Misc.getCState(getModel()));
		indexTab.addTabSelectedHandler(temp);
		indexTab.addTabDeselectedHandler(temp);
		indexTab.setPane(index);

		// final IController cont= this;
		addTab.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				final Canvas getTitle = new Canvas();
				getTitle.setTop(topTabSet.getTop() + 90);
				getTitle.setLeft(topTabSet.getLeft()
						+ topTabSet.getTabs().length * 70);

				getTitle.setBackgroundColor("white");

				final DynamicForm form = new DynamicForm();
				form.setGroupTitle("Add Tab");
				form.setIsGroup(true);
				form.setWidth(300);
				form.setHeight(120);
				form.setNumCols(2);
				form.setColWidths(60, "*");

				form.setPadding(5);
				form.setCanDragResize(true);
				form.setResizeFrom("R");

				final TextItem messageItem = new TextItem();
				messageItem.setTitle("Enter a page name ");

				messageItem.setColSpan(2);
				messageItem.setWidth("*");

				SubmitItem submit = new SubmitItem();
				submit.setTitle("submit");
				submit.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						for (Tab tab : topTabSet.getTabs()) {
							if (tab.getTitle().equalsIgnoreCase(messageItem.getValueAsString())){
								SC.say("You cannot make two tabs with the same name");
								return;
							}
							else if (!messageItem.getValueAsString().matches("^[a-zA-Z0-9_' ']*$")){
								SC.say("Invalid page name, only alpha numeric values are allowed ");
								return;
							}
							
						}
						getModel().run(
								new NewTabCommand(messageItem
										.getValueAsString(), model));

						topTabSet.removeTab(addTab);
						topTabSet.addTab(addTab);
						getTitle.destroy();

					}
				});

				SubmitItem cancel = new SubmitItem();
				cancel.setTitle("Cancel");
				cancel.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getTitle.setVisibility(Visibility.HIDDEN);
						topTabSet.selectTab(1);
					}
				});
				form.setFields(messageItem, submit, cancel);

				getTitle.addChild(form);
				getTitle.draw();
			}
		});

		this.addChild(topTabSet);
		this.setZIndex(getBottom());

		topTabSet.addTab(templateTab);
		topTabSet.addTab(indexTab);
		topTabSet.addTab(addTab);

		mainCanvas.addChild(this);
	}

	@Override
	public void registerModel(IModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void Update(IState state) {}

	public TabSet getTabSet() {
		return topTabSet;
	}
}