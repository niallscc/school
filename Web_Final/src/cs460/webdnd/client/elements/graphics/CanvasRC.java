/**
 * 
 */
package cs460.webdnd.client.elements.graphics;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author niallschavez this file does nothing. i was working on some graphics
 *         stuff briefly, but never got it working :/
 */
public class CanvasRC {

	public Context2d context;
	Canvas canvas = Canvas.createIfSupported();

	public CanvasRC(double x, double y, double w, double h, double r) {

		this.context = canvas.getContext2d();
		fillRoundedRect(x, y, w, h, r);
	}

	/*
	 * x: Upper left corner's X coordinate y: Upper left corner's Y coordinate
	 * w: Rectangle's width h: Rectangle's height r: Corner radius
	 */

	private void fillRoundedRect(double x, double y, double w, double h,
			double r) {

		// public Context2d context = canvas.getContext2d();
		context.beginPath();
		context.moveTo(x + r, y);
		context.lineTo(x + w - r, y);

		// context.arcTo(r, y+r, x+r, y, r);
		context.quadraticCurveTo(x + w, y, x + w, y + r);
		context.lineTo(x + w, y + h - r);
		context.quadraticCurveTo(x + w, y + h, x + w - r, y + h);
		context.lineTo(x + r, y + h);
		context.quadraticCurveTo(x, y + h, x, y + h - r);
		context.lineTo(x, y + r);
		context.quadraticCurveTo(x, y, x + r, y);
		context.stroke(); // if you need outline also
		context.fill(); // if you need fill in also
		context.save();
	}

	public Canvas getRoundedCanvas() {
		return canvas;
	}

	public Context2d getContext() {
		return context;
	}

}
