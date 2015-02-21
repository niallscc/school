/**
 * @author niallschavez
 * This is really really old... i dont think we use it anywhhere
 */

package cs460.webdnd.client.elements.workspace;

import java.util.LinkedList;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HoverEvent;
import com.smartgwt.client.widgets.events.HoverHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

import cs460.webdnd.client.mvc.controller.IController;

@Deprecated
public class Menu {
	protected IController controller;
	private Boolean created = false;
	protected final Canvas menuCreator = new Canvas();
	protected final Canvas mainCanvas;

	public Menu(IController cont, Canvas mainCanvas) {
		this.controller = cont;
		this.mainCanvas = mainCanvas;
	}

	public void create() {

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable throwable) {
				// StackTraceElement[] stackTraceElements =
				// throwable.getStackTrace();
				System.err
						.println("Youz gots an err0Rz. Kittehz rz stuk in C0Mpy. FinDz kittehz, Findz probremz");
				// System.err.println(stackTraceElements.toString());
			}
		});

		final DynamicForm menuForm = new DynamicForm();
		final LinkedList<FormItem> menuItems = new LinkedList<FormItem>();
		final Canvas menuCanvas = new Canvas();

		menuCreator.setBackgroundColor("#B8B8B8");
		menuCreator.setBorder("solid");
		menuCreator.setCanDragReposition(true);
		menuCreator.setCanDragResize(true);

		/*
		 * Create menustuff
		 */
		IButton addMenuItems = new IButton();
		addMenuItems.setTitle("Add Menu Items");
		addMenuItems.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				TextItem item = new TextItem();
				int itemNumber = menuItems.size() + 1;
				item.setTitle("Menu item " + itemNumber);
				menuItems.add(item);

				FormItem[] temp = new FormItem[menuItems.size()];
				for (int i = 0; i < menuItems.size(); i++) {
					temp[i] = menuItems.get(i);
				}
				menuForm.setFields(temp);
				menuForm.draw();
			}
		});
		/*
		 * Tab stuff
		 */
		final TabSet topTabSet = new TabSet();
		topTabSet.setTabBarPosition(Side.TOP);
		topTabSet.setWidth(600);
		topTabSet.setHeight(700);
		topTabSet.setLeft(350);
		topTabSet.setTop(100);

		/*
		 * Submit menu stuff
		 */
		IButton submitMenu = new IButton();
		submitMenu.setTitle("create menu");
		submitMenu.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				for (int i = 0; i < menuItems.size(); i++) {
					final Tab tTab = new Tab(menuItems.get(i).getValue()
							.toString());
					com.smartgwt.client.widgets.menu.Menu options = new com.smartgwt.client.widgets.menu.Menu();
					MenuItem addDropDown = new MenuItem();
					addDropDown.setTitle("Add a Drop Down Menu");

					addDropDown
							.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
								@Override
								public void onClick(MenuItemClickEvent event) {
									dropDownMenuCreator ddmc = new dropDownMenuCreator();
									Canvas c = ddmc.getddMenuCreator(tTab,
											topTabSet);

									menuCanvas.addChild(c);
									mainCanvas.addChild(menuCanvas);
									mainCanvas.redraw();
								}
							});
					options.addItem(addDropDown);
					tTab.setContextMenu(options);
					topTabSet.addTab(tTab);
					menuCanvas.addChild(topTabSet);
					menuCreator.destroy();
				}
			}
		});

		VStack lay = new VStack();
		HStack hlay = new HStack();
		lay.addMember(menuForm);
		hlay.addMember(addMenuItems);
		hlay.addMember(submitMenu);
		lay.addMember(hlay);
		menuCreator.addChild(lay);
		menuCreator.setTop(5);
		menuCreator.setLeft(400);

		menuCanvas.addChild(menuCreator);

		mainCanvas.addChild(menuCanvas);
		mainCanvas.draw();

	}

	protected class dropDownMenuCreator {

		public Canvas getddMenuCreator(final Tab menuParent,
				final TabSet topTabSet) {

			Canvas c = new Canvas();
			c.setTitle("add Drop Down Menu");
			c.setCanDragReposition(true);
			c.setCanDragResize(true);
			c.setTop(200);
			c.setLeft(200);
			c.setSize("200", "200");

			GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
				@Override
				public void onUncaughtException(Throwable throwable) {
					// StackTraceElement[] stackTraceElements =
					// throwable.getStackTrace();
					System.err
							.println("Youz gots an err0Rz. Kittehz rz stuk in C0Mpy. FinDz kittehz, Findz probremz");
					// System.err.println(stackTraceElements.toString());
				}
			});

			final DynamicForm menuForm = new DynamicForm();
			final LinkedList<FormItem> menuItems = new LinkedList<FormItem>();

			Canvas menuCreator = new Canvas();
			menuCreator.setBackgroundColor("#B8B8B8");
			menuCreator.setBorder("solid");
			menuCreator.setCanDragReposition(true);
			menuCreator.setCanDragResize(true);

			/*
			 * Create dropdownmenu
			 */

			IButton addMenuItems = new IButton();
			addMenuItems.setTitle("Add Menu Items");
			addMenuItems.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					TextItem item = new TextItem();
					int itemNumber = menuItems.size() + 1;
					item.setTitle("Menu item " + itemNumber);
					menuItems.add(item);

					FormItem[] temp = new FormItem[menuItems.size()];
					for (int i = 0; i < menuItems.size(); i++) {
						temp[i] = menuItems.get(i);
					}
					menuForm.setFields(temp);
					menuForm.draw();

				}
			});
			IButton submitMenu = new IButton();
			submitMenu.setTitle("create menu");
			submitMenu.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					topTabSet.addHoverHandler(new HoverHandler() {
						@Override
						public void onHover(HoverEvent event) {
							com.smartgwt.client.widgets.menu.Menu options = new com.smartgwt.client.widgets.menu.Menu();

							for (int i = 0; i < menuItems.size(); i++) {
								MenuItem addDropDown = new MenuItem();
								addDropDown.setTitle(menuItems.get(i)
										.getValue().toString());
								options.addItem(addDropDown);

							}
							menuParent.setContextMenu(options);

						}
					});
				}
			});
			VStack lay = new VStack();
			HStack hlay = new HStack();
			lay.addMember(menuForm);
			hlay.addMember(addMenuItems);
			hlay.addMember(submitMenu);
			lay.addMember(hlay);
			menuCreator.addChild(lay);
			return menuCreator;
		}

	}

	/**
	 * Returns whether a command has been isused that created this cavnas
	 */
	public boolean isCreated() {
		return created;
	}

	/**
	 * Says that a command has been issued and that this canvas has been created
	 */
	public void doCreate() {
		created = true;
	}

	public Canvas getMenuCanvas() {
		return menuCreator;
	}
}
