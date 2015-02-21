/**
 * @author niallschavez
 */
package cs460.webdnd.client.elements.themeCreator;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;

import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.commands.editor.SelectCommand;
import cs460.webdnd.client.mvc.controller.IController;

//public class DNDThemeCreator implements EntryPoint {
public class DNDThemeCreator {
	IController controller;
	private Boolean created = false;
	private Canvas canvasMain;

	public DNDThemeCreator(IController cont, Canvas parent) {
		this.canvasMain = parent;
		this.controller = cont;
	}

	Canvas header = spawnHeader();
	Canvas footer = spawnFooter();
	Canvas body = spawnBody();

	// public void onModuleLoad() {
	public void create() {
		/*
		 * Main window stuff
		 */
		Canvas window = new Canvas();
		// window.setPosition(Positioning.RELATIVE);
		// window.setAlign(Alignment.CENTER);
		window.setLeft("30%");
		window.setTop("20px");
		window.setTitle("Title of your website");
		window.setWidth("500px");
		window.setHeight("500px");
		window.setCanDragReposition(true);
		window.setCanDragResize(true);

		window.addChild(header);
		window.addChild(body);
		window.addChild(footer);
		window.setBorder("1px grey");

		/*
		 * End Main window stuff
		 */

		// This is the main encapsulating canvas

		canvasMain.addChild(window);
		canvasMain.draw();
	}

	private Canvas spawnBody() {
		/*
		 * Body of the Webpage
		 */
		final Canvas body = new Canvas();
		// body.setSize("300", "400");
		body.setPadding(10);
		body.setHeight100();
		body.setBorder("1px dashed");
		body.setPosition(Positioning.RELATIVE);
		body.setWidth100();
		body.setCanDragResize(true);
		body.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				controller.getModel().run(
						new SelectCommand(((CState) controller.getModel()
								.getState()), body));
			}
		});

		return body;
	}

	private Canvas spawnFooter() {
		/*
		 * footer of the webpage
		 */
		final Canvas footer = new Canvas();
		// header.setHeight100();
		footer.setPadding(10);
		footer.setBorder("1px dashed");
		footer.setPosition(Positioning.RELATIVE);
		footer.setWidth100();
		footer.setCanDragResize(true);

		footer.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				controller.getModel().run(
						new SelectCommand(((CState) controller.getModel()
								.getState()), footer));
			}
		});

		return footer;
	}

	private Canvas spawnHeader() {
		/*
		 * This is where header stuff goes
		 */

		final Canvas header = new Canvas();
		// header.setHeight100();
		header.setPadding(10);
		header.setBorder("1px dashed");
		header.setPosition(Positioning.RELATIVE);
		header.setWidth100();
		header.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				controller.getModel().run(
						new SelectCommand(((CState) controller.getModel()
								.getState()), header));
			}
		});

		return header;

	}

	/**
	 * Returns whether a command has been issued that created this canvas
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
}