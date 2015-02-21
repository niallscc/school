package old;


import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.smartgwt.client.widgets.Canvas;

/**
 * Tests the old serialization module.
 * @author Ian Mallett
 */
@Deprecated
public final class test extends GWTTestCase {
	@Test public final static void test_serialize_deserialize() {
		System.out.println("BEGINNING TEST");
		
		final Canvas c = new Canvas();
		System.out.println("CANVAS CREATED");
		
		final String result = serializer.serialize(c);
		System.out.println("CANVAS SERIALIZED: \""+result+"\"");
		
		final Canvas c2 = serializer.deserialize(result);
		System.out.println("CANVAS DESERIALIZED: \""+c2.toString()+"\"");
	}

	@Override public final String getModuleName() {
		return "cs460.webdnd.client.html.direct";
	}
}