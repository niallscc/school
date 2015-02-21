package cs460.webdnd.client.elements.images;

import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.handlers.SelectOnClickHandler;

/**
 * ImageRepositioning is a class which deals with images that are resizable and
 * able to be dragged and repositioned. In this class you will also find a
 * right-click handler for changing the size and opacity of an image.
 * 
 * John Schulz (jdschulz@cs.unm.edu)
 */
public class ImageRepositioning {

	/**
	 * Returns an image with a right-click context menu for adjusting its
	 * opacity and size.
	 * 
	 * @param path
	 *            - URL of the image
	 * @param width
	 *            - default width of an image
	 * @param height
	 *            - default height of an image
	 * @param left
	 *            - distance of an image from the left of its parent canvas
	 * @param top
	 *            - distance of an image from the top of its parent canvas
	 * @return The image specified by path with the given width/height, and
	 *         left/top
	 */
	public static Canvas imgItem(String path, int width, int height, int left, int top) {
		final Canvas test = new Canvas();
		test.setHeight(height);
		test.setWidth(width);
		test.setLeft(left);
		test.setTop(top);
		test.setUseOpacityFilter(true);
		test.setBackgroundImage(path);
		test.setRedrawOnResize(true);

		test.setKeepInParentRect(true);

		test.addMouseDownHandler(SelectOnClickHandler.getSelectionHandler(null));
		test.addMouseUpHandler(SelectOnClickHandler.getSelectionHandler(null));

		Menu dropDown = new Menu(), opacityMenu = new Menu(), sizeMenu = new Menu();
		MenuItem opacity = new MenuItem("opacity"), size = new MenuItem("size");

		MenuItem
			zero = new MenuItem("0%"), ten = new MenuItem("10%"),
			twenty = new MenuItem("20%"), thirty = new MenuItem("30%"),
			forty = new MenuItem("40%"), fifty = new MenuItem("50%"),
			sixty = new MenuItem("60%"), seventy = new MenuItem("70%"),
			eighty = new MenuItem("80%"), ninety = new MenuItem("90%"),
			hundred = new MenuItem("100%");

		class OpacityHandler implements com.smartgwt.client.widgets.menu.events.ClickHandler {

			OpacityHandler(int percent) {
				this.percentOpacity = percent;
			}

			int percentOpacity;

			@Override
			public void onClick(MenuItemClickEvent event) {
				test.setOpacity(this.percentOpacity);
			}

		};

		zero.addClickHandler(new OpacityHandler(0));
		ten.addClickHandler(new OpacityHandler(10));
		twenty.addClickHandler(new OpacityHandler(20));
		thirty.addClickHandler(new OpacityHandler(30));
		forty.addClickHandler(new OpacityHandler(40));
		fifty.addClickHandler(new OpacityHandler(50));
		sixty.addClickHandler(new OpacityHandler(60));
		seventy.addClickHandler(new OpacityHandler(70));
		eighty.addClickHandler(new OpacityHandler(80));
		ninety.addClickHandler(new OpacityHandler(90));
		hundred.addClickHandler(new OpacityHandler(100));

		MenuItem halfsize = new MenuItem("50%"), doublesize = new MenuItem(
				"200%");

		class SizeHandler implements
				com.smartgwt.client.widgets.menu.events.ClickHandler {

			SizeHandler(double decimal) {
				this.width = String.valueOf((int) (decimal * test.getWidth()));
				this.height = String
						.valueOf((int) (decimal * test.getHeight()));
			}

			String width, height;

			@Override
			public void onClick(MenuItemClickEvent event) {
				test.setSize(width, height);
			}

		}
		;
		halfsize.addClickHandler(new SizeHandler(0.5));
		doublesize.addClickHandler(new SizeHandler(2));

		opacityMenu.setItems(zero, ten, twenty, thirty, forty, fifty, sixty,
				seventy, eighty, ninety, hundred);
		opacity.setSubmenu(opacityMenu);

		sizeMenu.setItems(halfsize, doublesize);
		size.setSubmenu(sizeMenu);

		dropDown.addItem(opacity);
		dropDown.addItem(size);

		test.setContextMenu(dropDown);

		return test;
	}
}