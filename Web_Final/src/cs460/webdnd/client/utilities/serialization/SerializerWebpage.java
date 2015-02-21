package cs460.webdnd.client.utilities.serialization;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.elements.WebPage;

/**
 * Small class for serializing the special WebPage type.
 * 
 * @author Ian Mallett
 */
public class SerializerWebpage {
	private final static String separator = "WP_BLOCK";

	/**
	 * Serializes the WebPage.
	 * 
	 * @param object
	 *            the WebPage
	 * @return the serialized WebPage
	 */
	public final static String serialize(final WebPage object) {
		final String header = SerializerCanvas.serialize(object.getHeader());
		final String body = SerializerCanvas.serialize(object.getBody());
		final String footer = SerializerCanvas.serialize(object.getFooter());
		final String name = object.getName();

		return header + separator + body + separator + footer + separator
				+ name;
	}

	/**
	 * Deserializes the WebPage
	 * 
	 * @param object
	 *            the serialized WebPage
	 * @return a new WebPage
	 */
	public final static WebPage deserialize(final String object) {
		//final WebPage result = new WebPage();

		String[] data = object.split(separator);

		Canvas header = new Canvas();
		SerializerCanvas.deserialize(data[0], header);
		Canvas body = new Canvas();
		SerializerCanvas.deserialize(data[1], body);
		Canvas footer = new Canvas();
		SerializerCanvas.deserialize(data[2], footer);

		header.setPosition(Positioning.RELATIVE);
		body.setPosition(Positioning.RELATIVE);
		footer.setPosition(Positioning.RELATIVE);

		final WebPage result = new WebPage(data[3], header, body, footer);

		return result;
	}
}