package cs460.webdnd.client.elements.images;

import java.util.List;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.VStack;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.ImgEx;
import cs460.webdnd.client.elements.ToolCanvas;
import cs460.webdnd.client.handlers.AddImageHandler;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

/**
 * The ImageExplorer is a ToolCanvas that displays a list of Images. This is
 * setup to be a Singleton class as only one ImageExplorer should be created. As
 * such, the creation and population of the ImageExplorer are done separately.
 * 
 * @author Theodore Schnepper
 */
public class ImageExplorer extends ToolCanvas implements IView{
	protected static ImageExplorer ie;
	private VStack imageList;
	protected Canvas prevSel;

	private ImageExplorer(IController cont) {
		VStack v = new VStack();
		imageList = new VStack();
		imageList.setAlign(Alignment.CENTER);
		imageList.setLayoutMargin(20);
		imageList.setMembersMargin(40);
		imageList.setLayoutAlign(Alignment.CENTER);
		imageList.setShowEdges(true);
		imageList.setWidth100();
		imageList.setHeight100();
		imageList.setOverflow(Overflow.AUTO);
		this.addChild(v);
		v.addMember(imageList);
		v.setHeight100();
		v.setWidth100();
		IButton submit = new IButton("Insert");
		IButton cancel = new IButton("Cancel");
		cancel.addClickHandler(new ClickHandler() {
			@Override public void onClick(ClickEvent event) {
				ie.hide();
			}
		});
		submit.setPrompt("Click to add Image to current Selection");
		submit.addClickHandler(new AddImageHandler(cont, this));
		v.addMember(cancel);
		v.addMember(submit);
	}

	/***
	 * This first takes any previous elements in the ImageList and removes them,
	 * Then it takes the ImageList and adds them to the List.
	 * 
	 * @param imgLst
	 *            The List of Images to add to the ImageExplorer.
	 */
	private void populate(List<Img> imgLst) {
		//this.show();
		for (Canvas c : imageList.getChildren()) {
			c.destroy();
		}

		//final ImageExplorer ie = this;
		//System.out.println(imgLst.size());
		for (final Img i : imgLst) {

			final Canvas selection = new Canvas();
			selection.setBorder("thick double black");
			selection.setUseOpacityFilter(true);
			//selection.setPosition(Positioning.ABSOLUTE);
			i.setWidth100();
			i.setHeight100();
			ImgEx newI = new ImgEx(((ImgEx)i).getSrc(), ((ImgEx)i).getPath());
			selection.addChild(newI);
			imageList.addMember(selection);
			MouseHandlers mh = new MouseHandlers(selection, newI);
			selection.addMouseOverHandler(mh);
			selection.addMouseOutHandler(mh);
			selection.addClickHandler(mh);
		}
	}

	/***
	 * Creates the ImageExplorer if it doesn't exist and returns it if it does.
	 * 
	 * @param mainCanvas
	 *            The Canvas to add the ImageExplorer to
	 * @param cont
	 *            a IController to add to retrieve State Information from.
	 * @return The only copy of the ImageExplorer.
	 */
	public static ImageExplorer imageExplorer(Canvas mainCanvas,
			IController cont) {
		if (ie == null)
			ie = new ImageExplorer(cont);

		ie.setBackgroundColor("#B8B8B8");
		ie.setBorder("1px solid black");
		ie.setCanDragReposition(true);
		ie.setWidth(150);
		ie.setHeight(500);
		ie.setDragAppearance(DragAppearance.TARGET);
		ie.hide();

		return ie;
	}

	@Override
	public void Update(IState state) {
		CState cstate = Misc.getCState(state);
		if(cstate == null)
			return;
		//if(!this.isVisible())
		//	return;
		if(cstate.getImageList() == null)
			return;
		if(imageList.getMembers().length != cstate.getImageList().size())
			this.populate(cstate.getImageList());
	}
	
	/**
	 * This is a custom mouse handler class for images.  This class is utilized to show visual
	 * feedback when events happen in the Image Selection Process.
	 * 
	 * Upon mousing over an image, the border of the image will turn blue.
	 * 
	 * Upon Clicking on an Image, the border of the selection will turn red, and the previous
	 * selection's border will turn black.
	 * 
	 * Upon mousing out an image, the border of the image will turn black.
	 * 
	 * @author Theodore Schnepper
	 */
	private class MouseHandlers implements MouseOverHandler, MouseOutHandler, ClickHandler{
		private Canvas item;
		private Img img;
		private Canvas prevSelection;
		
		public MouseHandlers(Canvas item, Img img){
			this.item = item;
			this.img = img;
		}
		@Override
		public void onClick(ClickEvent event) {
			item.setBorder("thick double red");
			if(prevSelection != null){
				prevSelection.setBorder("thick double black");
			}
			ie.setSelection(img);
			prevSelection = item;
			
		}

		@Override
		public void onMouseOut(MouseOutEvent event) {
			if(item != prevSelection)
				item.setBorder("thick double black");
		}

		@Override
		public void onMouseOver(MouseOverEvent event) {
			if(item != prevSelection)
				item.setBorder("thick double blue");
			
		}
	}
}
