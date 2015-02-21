package old;

import old.serializer;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.smartgwt.client.widgets.Canvas;


/**
 * Test case for the serializer/deserializer.  It turns out that SmartGWT is broken
 * and so this test can't work (it would need an actual environment to run in).
 * 
 * @author Ian Mallett
 */
@Deprecated
public class test_serialize extends GWTTestCase {
	/** Tests basic serialization and deserialization */
	@Test public void test_serialize_deserialize() {
		System.out.println("BEGINNING TEST");
		
		Canvas c = new Canvas();
		System.out.println("CANVAS CREATED");
		
		String result = serializer.serialize(c);
		System.out.println("CANVAS SERIALIZED: \""+result+"\"");
		
		Canvas c2 = serializer.deserialize(result);
		System.out.println("CANVAS DESERIALIZED: \""+c2.toString()+"\"");
	}

	/** SmartGWT thought it needed this. */
	@Override public String getModuleName() {
		return "cs460.webdnd.client.html.direct";
	}
}