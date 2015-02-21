package cs460.webdnd.client.commands.startup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.util.SC;
import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.commands.CanvasHideCommand;
import cs460.webdnd.client.commands.CanvasShowCommand;
import cs460.webdnd.client.commands.UpdateImageListCommand;
import cs460.webdnd.client.commands.editor.FileUploadCommand;
import cs460.webdnd.client.commands.servercommunication.InitialLoadCommand;
import cs460.webdnd.client.elements.EditorTabSet;
import cs460.webdnd.client.elements.PrimaryMenuBar;
import cs460.webdnd.client.elements.PrimaryToolbar;
import cs460.webdnd.client.elements.SelectionMarquee;
import cs460.webdnd.client.elements.properties.PropertiesTab;
import cs460.webdnd.client.elements.workspace.CreatedTabs;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.utilities.Misc;
import cs460.webdnd.client.utilities.serialization.SerializerCanvas;

/**
 * This is the primary Initializing class. This class sets up the editor in a
 * blank state, so that it can be utilized/modified as appropriate. The main
 * purpose of this is to ensure that all the elements are initialized.
 * 
 * @author Theodore Schnepper
 */
@SuppressWarnings("deprecation") public class InitializeCommand implements ICommand {
	protected CState state;

	// protected FormPanel form;
	protected IController cont;
	// protected Label errorLabel;
	protected FlexTable uploadedTable;

	/**
	 * @param cont
	 */
	public InitializeCommand(IController cont) {
		this.cont = cont;
		this.state = Misc.getCState(cont);
		SelectOnClickHandler.getSelectionHandler(cont);


		GWT.setUncaughtExceptionHandler( new UncaughtExceptionHandler(){

			@Override public void onUncaughtException(Throwable e) { if
				(e.getCause().toString().equalsIgnoreCase("UmbrellaException")){
				System.err.println("There was an umbrella Exception that escaped from "+
						e.getCause() +"\n \n \n the stack trace is "+ e.getStackTrace()); } }
		});

	}

	@Override
	public void run() {

		SerializerCanvas.setIController(cont);
		/* File Upload */
		setFileUpload();

		state = Misc.getCState(cont);
		if (state == null)
			return;

		final Canvas mainCanvas = new Canvas();
		mainCanvas.setBackgroundColor("#505050");
		state.setMainCanvas(mainCanvas);

		FormPanel form = null;
		form = state.getUploadPanel();

		final PropertiesTab propwin = new PropertiesTab(cont.getModel());
		cont.getModel().addView(propwin);

		Canvas editorTabSet= new EditorTabSet(cont.getModel());
		mainCanvas.addChild(editorTabSet);

		mainCanvas.setSize("100%", "100%");

		PrimaryMenuBar menuBar = new PrimaryMenuBar(cont, mainCanvas);
		Canvas menu = menuBar.getMenuBar();


		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {

				String msg = event.getResults();

				if (msg.contains("ERROR")) {
					SC.say("ERROR: File Not Uploaded!");
					cont.getModel().run(new CanvasShowCommand(state.getFormCanvas()));
				} else {
					SC.say("File Uploaded Successfully.");
					cont.getModel().run(new CanvasHideCommand(state.getFormCanvas()));
					cont.getModel().run(new UpdateImageListCommand(cont.getModel()));
				}

			}
		});
		
		cont.getModel().addView(new PrimaryToolbar(cont, mainCanvas));
		mainCanvas.addChild(new SelectionMarquee(cont.getModel()));

		/**
		 * this method creates the canvas that allows users to pick between
		 * loading a previously created project or starting a new project
		 */
		// Canvas choice = getChoiceCanvas(mainCanvas);

		// mainCanvas.addChild(choice);

		mainCanvas.addChild(state.getFormCanvas());
		if (state.getMenuEditor() == null)
			state.setMenuEditor(new CreatedTabs(mainCanvas, cont.getModel()));

		//menu.show();

		mainCanvas.addChild(menu);
		mainCanvas.draw();
		cont.getModel().run(new InitialLoadCommand(cont.getModel()));
	}

	private final void setFileUpload() {
   
		// File upload stuff
		state.setFormCanvas(new Canvas());
		state.getFormCanvas().setBackgroundColor("#B8B8B8");
		state.getFormCanvas().setBorder("1px solid black");
		state.getFormCanvas().setCanDrag(true);
		state.getFormCanvas().setDragAppearance(DragAppearance.TARGET);
		state.getFormCanvas().setTop("50%");
		state.getFormCanvas().setWidth("50%");
		state.getFormCanvas().hide();

		FormPanel form = new FormPanel();
		if (cont.getModel().getState() instanceof CState) {
			CState state = (CState) cont.getModel().getState();
			state.setUploadPanel(form);
		}
		form.setAction(state.getUploadActionURL());
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		VerticalPanel vpan = new VerticalPanel();
		form.setWidget(vpan);

		com.google.gwt.user.client.ui.Button uploadSubmit = new com.google.gwt.user.client.ui.Button(
				"Upload it!");
		uploadSubmit
		.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(
					com.google.gwt.event.dom.client.ClickEvent event) {
				cont.getModel().run(new FileUploadCommand(cont));

			}

		});
		com.google.gwt.user.client.ui.Button cancel = new com.google.gwt.user.client.ui.Button(
				"Cancel");
		cancel.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {

			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				state.getFormCanvas().setVisibility(Visibility.HIDDEN);
			}

		});


		final FileUpload fileUpload = new FileUpload();
		fileUpload.setTitle("Upload an Image");
		fileUpload.setName("uploadFormElement");
		vpan.add(fileUpload);

		vpan.add(uploadSubmit);
		vpan.add(cancel);
		// form.setVisible(false);

		uploadedTable = new FlexTable();
		vpan.add(uploadedTable);
		/*
		 * end file upload stuff
		 */

		state.getFormCanvas().addChild(form);
	}
}
