/** 
 * This is the text Creator of awesomeness. 
 * Lets you drag a text box, make it bigger, add text, change font
 * blah blah blah.
 * @author niallschavez
 */
package cs460.webdnd.client.elements.textContent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.controller.IController;

public class Text {
	protected Canvas textCreate;
	protected static Canvas editorCanvas;
	protected static Canvas target;
	protected static RichTextEditor textEditor;
	private IController cont;

	public static Canvas getEditor(Canvas mainCanvas) {
		if (editorCanvas == null) {
			editorCanvas = new Canvas();
			editorCanvas.setCanDragResize(true);
			editorCanvas.setCanDragReposition(true);
			editorCanvas.setBackgroundColor("#B8B8B8");
			editorCanvas.setTop("50%");
			editorCanvas.setLeft("30%");
			editorCanvas.setVisibility(Visibility.HIDDEN);
			editorCanvas.setBorder("1px solid");
			mainCanvas.addChild(editorCanvas);

			textEditor = new RichTextEditor();
			textEditor.setWidth(600);
			textEditor.setHeight(200);
			textEditor.setCanDragScroll(true);

			Button submit = new Button();
			submit.setText("Submit");
			submit.setTitle("Submit");
			submit.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (target == null)
						return;
					// TODO: Make this into an IUndoableCommand
					target.setContents(textEditor.getValue());
					editorCanvas.hide();
					target = null;
					// target.redraw();
				}
			});
			Button cancel = new Button();
			cancel.setText("Cancel");
			cancel.setTitle("Cancel");
			cancel.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					editorCanvas.setVisibility(Visibility.HIDDEN);
				}
			});

			HLayout buttonLayout = new HLayout();
			buttonLayout.addMember(submit);
			buttonLayout.addMember(cancel);

			VLayout layout = new VLayout(5);
			layout.addMember(textEditor);
			layout.addMember(buttonLayout);
			editorCanvas.addChild(layout);
		}
		return editorCanvas;
	}

	@SuppressWarnings("unused")
	private void create() {

		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable throwable) {
				// StackTraceElement[] stackTraceElements =
				// throwable.getStackTrace();
				// System.err.println("Youz gots an err0Rz. Kittehz rz stuk in C0Mpy. FinDz kittehz, Findz probremz");
				// System.err.println(stackTraceElements.toString());
			}
		});

		// editorCanvas= new Canvas();
		textCreate = new Canvas();
		textCreate.setCanDragResize(true);
		textCreate.setCanDragReposition(true);
		textCreate.setSize("80", "80");
		textCreate.setContextMenu(new Menu());
		textCreate.setTop("50%");
		textCreate.setLeft("50%");
		textCreate.setTitle("Right click to edit text.");
		textCreate.addChild(new HTML("Right click to edit text"));
		textCreate.setBorder("1px dotted");
		textCreate.addMouseDownHandler(SelectOnClickHandler
				.getSelectionHandler(cont));

		textCreate.addRightMouseDownHandler(new RightMouseDownHandler() {
			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				if (editorCanvas.getVisibility().equals(Visibility.HIDDEN)) {
					editorCanvas.setVisibility(Visibility.VISIBLE);
					textCreate.setBorder("");
				}
			}
		});
	}

	public static void edit(final Canvas item) {
		item.addRightMouseDownHandler(new RightMouseDownHandler() {

			@Override
			public void onRightMouseDown(RightMouseDownEvent event) {
				if (target != null)
					return;

				target = item;
				textEditor.setValue(item.getContents());
				editorCanvas.show();
			}

		});
	}

	public Canvas getCanvas() {
		return textCreate;
	}
}