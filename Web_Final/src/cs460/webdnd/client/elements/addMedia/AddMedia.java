package cs460.webdnd.client.elements.addMedia;

import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.handlers.SelectOnClickHandler;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

/**
 * This allows the users to add different types of embedded media into their webpage, like youtube videos and music :)
 * 
 * @author niallschavez
 */
public class AddMedia {
	private IModel model;
	protected Canvas insertMedia;
	protected CState state;
	protected RadioGroupItem mediaPicker;
	protected TextItem messageItem; 
	public AddMedia(IModel model) {
		insertMedia = new Canvas();
		mediaPicker=new RadioGroupItem();
		messageItem= new TextItem("value");
		this.model = model;
		create();
	}

	public void create() {
		/**
		 * Creating the editor canvas
		 */
		
		insertMedia.setTop("40%");
		insertMedia.setLeft("30%");
		insertMedia.setBorder("1px solid");
		insertMedia.setBackgroundColor("white");
		insertMedia.setCanDragReposition(true);

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Insert link and SELECT the kind of media you would like to add");
		form.setIsGroup(true);
		form.setWidth(300);
		form.setHeight(180);
		form.setNumCols(2);
		form.setColWidths(60, "*");
		form.setPadding(5);
		form.setCanDragResize(true);
		form.setResizeFrom("R");

		messageItem.setShowTitle(false);
		messageItem.setLength(5000);
		messageItem.setColSpan(2);
		messageItem.setWidth("*");
		messageItem.setHeight("*");


		mediaPicker.setTitleOrientation(TitleOrientation.TOP);
		mediaPicker.setTitle("Media type");
		mediaPicker.setValueMap("Video","Music");
		
		SubmitItem submit = new SubmitItem();
		submit.setTitle("Submit");
		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//SC.say("Inside the click event");
				
				
				
				if(mediaPicker.getValueAsString().equals("Video")){
					Canvas mediaCanvas = new Canvas();
					mediaCanvas.setBorder("5px solid");
					//mediaCanvas.setContents("<iframe width=\"560 \" height= \"315\" src=\" "+form.getValueAsString("value")+ "\" frameborder=\"0\" allowfullscreen></iframe>");
					SelectOnClickHandler.giveSelectHandler(mediaCanvas);
					if(state.getSelection() != null){
						SC.say("Added video "+ messageItem.getValueAsString() );
						state.getSelection().addChild(mediaCanvas);
						insertMedia.setVisibility(Visibility.HIDDEN);
					}
					else 
						SC.say("Please make a selection");
				}
				else{
					SC.say("music");
					if(state.getSelection()!= null){
						Canvas mediaCanvas = new Canvas();
						mediaCanvas.setContents("<embed src=\""+form.getValueAsString("value")+" \" width=\"300\" height=\"42\" ></embed>");
						state.getSelection().addChild(mediaCanvas);
						insertMedia.setVisibility(Visibility.HIDDEN);
					}
				}
				
			}
		});

		SubmitItem cancel = new SubmitItem();
		cancel.setTitle("Cancel");
		cancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				insertMedia.setVisibility(Visibility.HIDDEN);
			}
		});

		form.setFields(messageItem,mediaPicker, submit, cancel);

		insertMedia.addChild(form);
		Misc.getCState(model).getMainCanvas().addChild(insertMedia);
	}
	
	public Canvas getMediaCanvas(){
		return insertMedia;
	}
}