package cs460.webdnd.client.elements;

import com.google.gwt.event.shared.HandlerRegistration;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;

import cs460.webdnd.client.handlers.SelectOnClickHandler;

/***
 * This is the primary representative of the overall Website. Every Page will be
 * able to built independently of all other pages. That being said, every page
 * must be able to display elements from the Template.
 * 
 * @author Theodore Schnepper
 * 
 */
public class WebPage extends Canvas {
	private String name;
	private Canvas container;
	private Canvas templateContainer;
	private Canvas header;
	private Canvas body;
	private Canvas footer;
	private Canvas divider1;
	private Canvas divider2;

	private Canvas tempHeader;
	private Canvas tempBody;
	private Canvas tempFooter;
	private Canvas tempDivider1;
	private Canvas tempDivider2;

	private HandlerRegistration headerHandler;
	private HandlerRegistration bodyHandler;
	private HandlerRegistration footerHandler;

	// private IController cont;
	public WebPage(String name, Canvas header, Canvas body, Canvas footer) {
		importElements(header, body, footer, name);
	}

	public WebPage(String name) {
		super();

		boolean template = (name.equalsIgnoreCase("template"));

		Canvas header = new Canvas();
		header.setPosition(Positioning.RELATIVE);
		header.setHeight("90px");
		header.setWidth("800px");
		// header.setTop(0);
		header.setOverflow(Overflow.HIDDEN);
		if (template)
			header.setBackgroundColor("#ffffff");

		Canvas body = new Canvas();
		body.setPosition(Positioning.RELATIVE);
		body.setHeight("480px");
		body.setWidth("800px");
		// body.setTop(10);
		if (template)
			body.setBackgroundColor("#ffffff");

		Canvas footer = new Canvas();
		footer.setPosition(Positioning.RELATIVE);
		footer.setHeight("30px");
		footer.setWidth("800px");
		// footer.setTop(20);
		footer.setOverflow(Overflow.HIDDEN);
		if (template)
			footer.setBackgroundColor("#ffffff");

		importElements(header, body, footer, name);
	}

	private void importElements(Canvas header, Canvas body, Canvas footer,
			String name) {
		this.name = name;
		this.header = header;
		this.body = body;
		this.footer = footer;

		this.setPosition(Positioning.ABSOLUTE);
		this.setTop(0);
		this.setLeft(0);

		this.setHeight100();

		this.setBackgroundImage("http://1.bp.blogspot.com/-H-AleANAgGc/TcBTZ8MgaAI/AAAAAAAAALQ/hoFSRYCNkLE/s320/checker.png"); // /
																																// Just
																																// testing
		container = new Canvas();
		container.setPosition(Positioning.ABSOLUTE);
		container.setTop(0);
		container.setLeft(0);
		container.setWidth100();
		container.setHeight100();
		container.setStyleName("wp_container");

		templateContainer = new Canvas();
		templateContainer.setPosition(Positioning.ABSOLUTE);
		templateContainer.setTop(0);
		templateContainer.setLeft(0);
		templateContainer.setWidth100();
		templateContainer.setHeight100();
		templateContainer.setStyleName("wp_tcontainer");

		addHandlers(header);
		addHandlers(body);
		addHandlers(footer);

		divider1 = new Canvas();
		divider1.setHeight(10);
		divider1.setWidth100();
		divider1.setPosition(Positioning.RELATIVE);

		divider2 = new Canvas();
		divider2.setHeight(10);
		divider2.setWidth100();
		divider2.setPosition(Positioning.RELATIVE);

		tempDivider1 = new Canvas();
		tempDivider1.setHeight(10);
		tempDivider1.setWidth100();
		tempDivider1.setPosition(Positioning.RELATIVE);

		tempDivider2 = new Canvas();
		tempDivider2.setHeight(10);
		tempDivider2.setWidth100();
		tempDivider2.setPosition(Positioning.RELATIVE);

		container.addChild(header);
		container.addChild(divider1);
		container.addChild(body);
		container.addChild(divider2);
		container.addChild(footer);

		this.addChild(templateContainer);
		this.addChild(container);
	}

	private void addHandlers(Canvas c) {
		SelectOnClickHandler.giveSelectHandler(c);
		for (Canvas c1 : c.getChildren())
			addHandlers(c1);
	}

	public Canvas getHeader() {
		return header;
	}

	public void setHeader(Canvas header) {
		this.header = header;
	}

	public Canvas getBody() {
		return body;
	}

	public void setBody(Canvas body) {
		this.body = body;
	}

	public Canvas getFooter() {
		return footer;
	}

	public void setFooter(Canvas footer) {
		this.footer = footer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void UpdateTemplate(WebPage template) {
		if (template == null)
			return;

		this.addChild(templateContainer);
		this.addChild(container);

		if(tempDivider1.getParentElement() != null)
			templateContainer.removeChild(tempDivider1);
		if(tempDivider2.getParentElement() != null)
			templateContainer.removeChild(tempDivider2);

		templateContainer.addChild(template.getHeader());
		templateContainer.addChild(tempDivider1);
		templateContainer.addChild(template.getBody());
		templateContainer.addChild(tempDivider2);
		templateContainer.addChild(template.getFooter());

		template.getHeader().setHeight(getHeader().getHeightAsString());
		template.getBody().setHeight(getBody().getHeightAsString());
		template.getFooter().setHeight(getFooter().getHeightAsString());

		template.getHeader().disable();
		template.getBody().disable();
		template.getFooter().disable();

		tempHeader = template.getHeader();
		tempBody = template.getBody();
		tempFooter = template.getFooter();

		if (headerHandler != null)
			headerHandler.removeHandler();

		if (bodyHandler != null)
			bodyHandler.removeHandler();

		if (footerHandler != null)
			footerHandler.removeHandler();

		headerHandler = this.header.addResizedHandler(new MirrorResize(header,
				tempHeader));
		bodyHandler = this.body.addResizedHandler(new MirrorResize(body,
				tempBody));
		footerHandler = this.footer.addResizedHandler(new MirrorResize(footer,
				tempFooter));
	}

	public void restore() {
		header.enable();
		body.enable();
		footer.enable();

		this.addChild(templateContainer);
		this.addChild(container);

		container.removeChild(divider1);
		container.removeChild(divider2);

		container.addChild(header);
		container.addChild(divider1);
		container.addChild(body);
		container.addChild(divider2);
		container.addChild(footer);
	}

	/***
	 * This is meant to keep two Canvases the same size.
	 * 
	 * @author Theodore Schnepper
	 */
	private class MirrorResize implements ResizedHandler {
		private Canvas primaryCanvas;
		private Canvas mirroredCanvas;

		public MirrorResize(Canvas primaryCanvas, Canvas mirroredCanvas) {
			this.primaryCanvas = primaryCanvas;
			this.mirroredCanvas = mirroredCanvas;
		}

		@Override
		public void onResized(ResizedEvent event) {
			if (mirroredCanvas == null || primaryCanvas == null)
				return;
			mirroredCanvas.setHeight(primaryCanvas.getHeightAsString());
			mirroredCanvas.setWidth(primaryCanvas.getWidthAsString());
		}
	}
}