package cs460.webdnd.client.utilities.html_converter;

import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.utilities.serialization.SerializerCanvas;

/**
 * Generates HTML body. This file contains older solutions that have merit, but
 * are currently not being used.
 * 
 * @author Ian Mallett
 */
public final class GenerateBody {
	// protected static String html;

	/*
	 * private final static void get_recursively(StringBuilder sb, Canvas
	 * canvas, int depth) { //for (int i=0;i<depth;++i)
	 * System.out.print("    "); sb.append(canvas.getElement().getInnerHTML());
	 * //System.out.println(canvas.getElement().getInnerHTML()); for (Canvas
	 * canvas2 : canvas.getChildren()) get_recursively(sb,canvas2,depth+1); }
	 */

	/**
	 * Generates the body text of the Canvas. It turns out that the SmartGWT
	 * Canvas object is buggy (go figure) and doesn't understand that getters
	 * shouldn't change stuff (at least significantly); if you call
	 * "String .getInnerHTML()", the Canvas's internal state is drastically
	 * changed for some reason, and the Canvas will not work correctly anymore.
	 * 
	 * The workaround in this case is to simply copy the canvas with the
	 * serializer/deserializer, call "String .getInnerHTML()" on *that*, and
	 * then remove the copy (which for some, perhaps more reasonable reason)
	 * adds itself to the project (so we have to .destroy()).
	 * 
	 * @param canvas
	 *            the Canvas to render.
	 * @return the raw text of the Canvas's HTML
	 */
	public final static String generate(final Canvas canvas) {
		/*
		 * final PrintHTMLCallback callback = new PrintHTMLCallback() {
		 * 
		 * @Override public void setHTML(String html2) {
		 * System.out.println("ADDING RAW HTML: \""+html2+"\"!"); html = html2;
		 * } }; final PrintProperties props = new PrintProperties();
		 * canvas.getPrintHTML(props,callback);
		 */
		// html = canvas.getContents();

		// return html;

		/*
		 * String body; { StringBuilder sb = new StringBuilder();
		 * get_recursively(sb,canvas,0); body = sb.toString(); } return body;
		 */

		// Make a copy and get the result of that because GWT is buggy and
		// doesn't understand
		// that a getter shouldn't screw stuff up.
		final Canvas temp = new Canvas();
		{
			final String serialized = SerializerCanvas.serialize(canvas);
			SerializerCanvas.deserialize(serialized, temp);
			// System.out.println("Serialized copy: \""+serialized+"\"");
		}

		final String result = temp.getElement().getInnerHTML();
		// System.out.println(canvas + "Got inner HTML: \""+result+"\"");
		temp.destroy();

		return result;
	}
}