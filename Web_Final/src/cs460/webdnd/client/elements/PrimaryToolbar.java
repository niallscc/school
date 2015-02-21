/**
 * @author niallschavez
 * @author theayiga
 */
package cs460.webdnd.client.elements;

import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.CanvasShowCommand;
import cs460.webdnd.client.commands.editor.AddCanvasCommand;
import cs460.webdnd.client.commands.editor.MediaCommand;
import cs460.webdnd.client.commands.editor.MenuStyleCommand;
import cs460.webdnd.client.commands.editor.TextCommand;
import cs460.webdnd.client.elements.images.ImageExplorer;
import cs460.webdnd.client.elements.textContent.Text;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

public class PrimaryToolbar implements IView, IController {
	protected IModel model;
	private ToolStrip strip;
	// private ToolStripButton addForm;
	private ToolStripButton addMedia;
	private ToolStripButton addText;
	private ToolStripButton addMenu;
	private ToolStripButton addCanvas;
	private ToolStripButton addImage;
	private ToolStripButton uploadFile;
	private ToolStripButton undo;
	private ToolStripButton redo;
	protected ImageExplorer ie;

	public PrimaryToolbar(final IController cont, final Canvas mainCanvas) {
		// this.cont= cont;
		this.model = cont.getModel();
		strip = new ToolStrip();
		strip.setVertical(true);
		strip.setWidth(30);
		strip.addSeparator();

		/*****************
		 * Undo Button *
		 *****************/
		undo = new ToolStripButton();
		undo.setActionType(SelectionType.BUTTON);
		undo.setIcon("[SKIN]/PrimaryToolbar/undo.png");
		undo.setPrompt("Undo previous Action");
		undo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.undo();
			}

		});
		strip.addButton(undo);
		undo.disable();

		/*****************
		 * Redo Button *
		 *****************/
		redo = new ToolStripButton();
		redo.setActionType(SelectionType.BUTTON);
		redo.setIcon("[SKIN]/PrimaryToolbar/redo.png");
		redo.setPrompt("Redo previous undone action");
		redo.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				model.redo();
			}

		});
		strip.addButton(redo);
		redo.disable();

		strip.addSeparator();

		/*****************
		 * Menu Creation *
		 *****************/

		addMenu = new ToolStripButton();
		addMenu.setIcon("[SKIN]/PrimaryToolbar/addmenu.png");
		addMenu.setActionType(SelectionType.BUTTON);
		addMenu.setPrompt("Add a navigational menu");
		addMenu.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CState state = Misc.getCState(model);
				if (state.getSelection() != null) {
					MenuStyleCommand msc = new MenuStyleCommand(cont, state
							.getSelection(), mainCanvas);
					msc.run();
				}
			}
		});
		strip.addButton(addMenu);
		addMenu.disable();

		/*****************
		 * Text Creation *
		 *****************/
		Text.getEditor(mainCanvas); // Creating the TextEditor

		addText = new ToolStripButton();
		addText.setIcon("[SKIN]/PrimaryToolbar/text.png");
		addText.setActionType(SelectionType.BUTTON);
		addText.setPrompt("Add Text to Selected Element");
		addText.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CState state = Misc.getCState(model);
				if (state.getSelection() != null) {
					TextCommand tc = new TextCommand(cont, state.getSelection());
					tc.run();
				}
			}
		});
		strip.addButton(addText);
		addText.disable();

		/*****************
		 * Form Creation * Deprecated *
		 *****************/
		/*
		 * addForm = new ToolStripButton();
		 * 
		 * addForm.setIcon("[SKIN]/DynamicForm/PopUpTextAreaEditor_icon.gif");
		 * addForm.setActionType(SelectionType.BUTTON);
		 * addForm.setPrompt("Add a Form"); addForm.addClickHandler(new
		 * ClickHandler(){
		 * 
		 * @Override public void onClick(ClickEvent event) { CState state =
		 * misc.getCState(model); if(!state.getSelection().equals(null)){
		 * FormCommand nfc= new FormCommand(cont, state.getSelection(),
		 * mainCanvas); nfc.run(); } } }); strip.addButton(addForm);
		 * addForm.disable();
		 */
		/*****************
		 * Canvas Creation*
		 *****************/
		addCanvas = new ToolStripButton();
		addCanvas.setIcon("[SKIN]/PrimaryToolbar/addcanvas.png");
		addCanvas.setActionType(SelectionType.BUTTON);
		addCanvas.setPrompt("Add a Div to the Selected Element");
		addCanvas.addClickHandler(new AddCanvasHandler(this));
		strip.addButton(addCanvas);
		addCanvas.disable();

		this.ie = ImageExplorer.imageExplorer(mainCanvas, this);
		model.addView(this.ie);

		/******************
		 * Image Creation *
		 ******************/
		addImage = new ToolStripButton();
		addImage.setIcon("[SKIN]/PrimaryToolbar/addimg.png");
		addImage.setActionType(SelectionType.BUTTON);
		addImage.setPrompt("Add an Image to the Selected Element");
		addImage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//List<Img> images = new ArrayList<Img>();
				//CState state = Misc.getCState(model);
				//state.getDataService().getFiles(
						//new PopulateImageList(images, model, ie));
				model.run(new CanvasShowCommand(ie));
			}

		});
		strip.addButton(addImage);
		addImage.disable();

		/***************
		 * Add media *
		 ***************/
		addMedia = new ToolStripButton();
		addMedia.setIcon("[SKIN]/PrimaryToolbar/addmedia.png");
		addMedia.setActionType(SelectionType.BUTTON);
		addMedia.setPrompt("Add media");
		addMedia.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final CState state = Misc.getCState(model);
				if (state == null)
					return;

				if (state.getSelection() == null)
					SC.say("Please make a selection first.");
				else {
					model.run(new MediaCommand(model));
				}
			}
		});
		strip.addButton(addMedia);
		addMedia.disable();

		strip.addSeparator();

		/*****************
		 * File Upload *
		 *****************/
		uploadFile = new ToolStripButton();
		uploadFile.setIcon("[SKIN]/MultiUploadItem/icon_add_Files.png");
		uploadFile.setActionType(SelectionType.BUTTON);
		uploadFile.setPrompt("Upload a File");
		uploadFile.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CState state = Misc.getCState(model);
				if (state == null)
					return;

				if (state.getFormCanvas() == null)
					return;

				state.getFormCanvas().show();
			}
		});
		strip.addButton(uploadFile);

		strip.setCanDrag(true);
		strip.setDragAppearance(DragAppearance.TARGET);
		strip.setTop(50);
		strip.setLeft(0);
		mainCanvas.addChild(strip);
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
	public void Update(IState state) {

		if (!(state instanceof CState))
			return;

		CState cstate = (CState) state;

		if (this.model.canRedo())
			redo.enable();
		else
			redo.disable();

		if (this.model.canUndo())
			undo.enable();
		else
			undo.disable();

		if (cstate.getSelection() == null) {
			// addForm. disable();
			addMenu.disable();
			addText.disable();
			addImage.disable();
			addCanvas.disable();
			addMedia.disable();
		} else {

			// addForm. enable();
			addMenu.enable();
			addText.enable();
			addImage.enable();
			addCanvas.enable();
			addMedia.enable();
		}
	}

	private class AddCanvasHandler implements ClickHandler {
		private IController cont;

		public AddCanvasHandler(IController cont) {
			this.cont = cont;
		}

		@Override
		public void onClick(ClickEvent event) {
			if (!(cont.getModel().getState() instanceof CState))
				return;

			CState state = (CState) cont.getModel().getState();

			if (state.getSelection() == null)
				return;

			model.run(new AddCanvasCommand(cont, state.getSelection()));
		}
	}
}