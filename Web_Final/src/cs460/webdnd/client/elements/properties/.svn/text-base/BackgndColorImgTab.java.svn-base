package cs460.webdnd.client.elements.properties;

import java.util.LinkedHashMap;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ColorPickerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.commands.SetBackgroundCommand;
import cs460.webdnd.client.elements.images.ImageSelector;
import cs460.webdnd.client.elements.images.ImageRepositioning;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

/**
 * The BackgndColorImgTab lets the user change background color and background
 * and image for a selected canvas.
 * 
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
public class BackgndColorImgTab extends Tab implements IView, IController {

	protected IModel model;

	protected ColorPickerItem bgColor;
	protected CheckboxItem useBGImage;
	protected RadioGroupItem tiling;
	protected ImageSelector is;
	protected IButton submit;

	protected static LinkedHashMap<String, String> colorMap;

	public BackgndColorImgTab(final IModel model) {

		this.model = model;

		this.setTitle("Background");

		CState cstate = Misc.getCState(model);

		VStack bgStack = new VStack();
		is = ImageSelector.imageSelector(cstate.getMainCanvas());
		is.setWidth100();
		
		bgColor = new ColorPickerItem();
		bgColor.setWidth("100px");
		bgColor.setHeight("50px");
		bgColor.setTitle("<b>Background Color</b>");
		bgColor.setTitleOrientation(TitleOrientation.TOP);

		useBGImage = new CheckboxItem();
		useBGImage.setTitle("<b>Use Background Image</b>");
		useBGImage.setTitleOrientation(TitleOrientation.RIGHT);

		IButton submit = new IButton("Submit");

		DynamicForm form = new DynamicForm();
		form.setFields(bgColor, useBGImage);

		Canvas label = new Canvas();
		label.setWidth100();
		label.setHeight(15);
		label.setContents("<b>Select Image</b>");

		bgStack.addMember(form);
		bgStack.addMember(label);
		bgStack.addMember(is);
		bgStack.addMember(submit);

		submit.addClickHandler(new ChangeBackgroundHandler());
		
		this.setPane(bgStack);
	}

	@Override
	public void Update(IState state) {
		CState cstate = Misc.getCState(model);
		if (state == null)
			return;

		Canvas cIn = cstate.getSelection();

		//List<Img> images = new ArrayList<Img>();
		//cstate.getDataService().getFiles(
				//new PopulateImageList(images, model, is));
		if(cstate.getImageList() != null)
			is.populate(cstate.getImageList());

		if (cIn == null) {
			bgColor.setValue("#FFFFFF");
			bgColor.disable();
			useBGImage.setValue(false);
			useBGImage.setDisabled(true);
		} else {
			//SC.say("Setting background to color: "+ bgColor.getValueAsString());
			bgColor.setValue(cIn.getBackgroundColor());
			//cIn.setBackgroundColor(bgColor.getValueAsString());
			bgColor.enable();
			useBGImage.setDisabled(false);
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
	 * ClickHandler attached to the submit button for BackgndColorImgTab which
	 * calls the command for setting background image or color as long as a
	 * canvas is selected.
	 * 
	 * @author John Schulz (jdschulz@cs.unm.edu)
	 */
	protected class ChangeBackgroundHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			CState state = Misc.getCState(model);
			if (state == null){
				SC.say("State is null!");
				return;
			}
			if (state.getSelection() == null){
				SC.say("selection is null!");
				return;
			}
				

			if (useBGImage.getValueAsBoolean()) {
				SC.say("Using an image");
				Img img = (Img) (is.getSelection());
				if (is.getSelection() == null)
					SC.say("Please select an image");

				else if (img == null)
					SC.say("Please select an image.");
				else if (img.getSrc() == null)
					SC.say("Image source returned null, please try again");
				else if (img.getWidth() == null)
					SC.say("Image width returned null, please try again");
				else if (img.getHeight() == null)
					SC.say("Image Height returned null, please try again");
				else {
					Canvas newImg = ImageRepositioning.imgItem(img.getSrc(),
							img.getWidth(), img.getHeight(), 0, 0);
					model.run(new SetBackgroundCommand(state.getSelection(),
							newImg.getBackgroundImage(), null));
				}
			} else{
				//state.getSelection().setBackgroundColor(bgColor.getValueAsString());
				model.run(new SetBackgroundCommand(state.getSelection(),
						null, bgColor.getValueAsString()));
				
			}
		}
	}

	/**
	 * Asynchronous callback method for populating the instance of ImageSelector
	 * in the BackgndColorImgTab.
	 * 
	 * @author John Schulz (jdschulz@cs.unm.edu)
	 */
	/*private class PopulateImageList implements
			AsyncCallback<Response<LinkedList<FileInformation>>> {

		private List<Img> images;
		private CState state;
		private ImageSelector is;

		public PopulateImageList(List<Img> images, IModel model,
				ImageSelector is) {
			this.images = images;
			this.state = Misc.getCState(model);
			this.is = is;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Response<LinkedList<FileInformation>> result) {

			if (result == null || result.getResponse() != Response.Res.ALL_OK) {
				SC.say("Unable to Retrived File List: "
						+ result.getResponse().toString());
				return;
			}
			images.clear();
			for (FileInformation f : result.getFile()) {
				if (f != null && f.getType() == FileType.IMAGE) {
					String path = state.getUploadActionURL() + "?thumb="
							+ f.getFileName();
					Img i = new Img(path);
					System.out.println("list populated");
					images.add(i);
				}
			}
			is.populate(images);
		}
	}*/
}
