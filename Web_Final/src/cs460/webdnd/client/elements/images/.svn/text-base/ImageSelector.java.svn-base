package cs460.webdnd.client.elements.images;

import java.util.List;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.VStack;

import cs460.webdnd.client.elements.ImgEx;
import cs460.webdnd.client.elements.ToolCanvas;

/**
 * Similar to ImageExplorer, ImageSelector populates a canvas with thumbnails
 * representing the images uploaded to the server.
 * 
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
public class ImageSelector extends ToolCanvas {
	protected static ImageSelector is;
	private VStack imageList;
	protected Canvas prevSel;

	private ImageSelector() {
		VStack v = new VStack();
		imageList = new VStack();
		imageList.setAlign(Alignment.CENTER);
		imageList.setLayoutMargin(20);
		imageList.setMembersMargin(40);
		imageList.setLayoutAlign(Alignment.CENTER);
		imageList.setShowEdges(true);
		imageList.setWidth100();
		imageList.setHeight100();
		this.addChild(v);
		this.setHeight("500px");
		this.setWidth100();
		v.addMember(imageList);
		v.setHeight100();
		v.setWidth100();
	}

	/**
	 * Populates the ImageSelector with thumbnails from all the images uploaded
	 * to the server, adds MouseOver and MouseOut handlers to let the user know
	 * with colored borders around thumbnails that they are in the process of
	 * selecting an image, and adds a ClickHandler to change the thumbnail
	 * border, letting them know they made a selection.
	 * 
	 * @param imgLst
	 */
	public void populate(List<Img> imgLst) {
		//this.show();

		for (Canvas c : imageList.getChildren()) {
			c.destroy();
		}

		//final ImageSelector is = this;
		//System.out.println(imgLst.size());
		for (final Img i : imgLst) {

			//System.out.println("Added Image!");

			final Canvas selection = new Canvas();
			selection.setBorder("thick double black");
			selection.setUseOpacityFilter(true);
			selection.setPosition(Positioning.ABSOLUTE);
			i.setWidth100();
			i.setHeight100();
			ImgEx newI = new ImgEx(((ImgEx)i).getSrc(), ((ImgEx)i).getPath());
			selection.addChild(newI);
			imageList.addMember(selection);

			MouseHandlers mh = new MouseHandlers(selection, newI);
			selection.addClickHandler(mh);
			selection.addMouseOverHandler(mh);
			selection.addMouseOutHandler(mh);
		}
	}

	public static ImageSelector imageSelector(Canvas mainCanvas) {
		if (is == null)
			is = new ImageSelector();

		is.setBackgroundColor("#B8B8B8");
		is.setBorder("1px solid black");
		is.setWidth100();
		is.setHeight(500);
		//is.setDragAppearance(DragAppearance.TARGET);
		//is.hide();

		return is;
	}
	
	/***
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
	 *
	 */
	private class MouseHandlers implements MouseOverHandler, MouseOutHandler, ClickHandler{
		private Canvas item;
		private Canvas prevSelection;
		private Img img;
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
			is.setSelection(img);
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