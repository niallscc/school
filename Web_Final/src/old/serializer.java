package old;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import cs460.webdnd.client.utilities.Misc;

/**
 * Changes objects to serialized objects and vice-versa.  This was the first (real) version of the
 * serializer/deserializer.  Unfortunately, GWT doesn't do it right, and so there are issues.  See
 * my blog post on the topic: http://iancs460.blogspot.com/2012/03/serialization-update-2.html.
 * 
 * Consequently, this module is deprecated.
 * 
 * @author Ian Mallett
 */
@Deprecated
public final class serializer {
	private final static void warn_serialize(final Exception e) {
		Misc.warn(": \"", Misc.MODULE.MODULE_SERIALIZE);
		e.printStackTrace();
		Misc.warn("\"", Misc.MODULE.MODULE_SERIALIZE);
	}
	
	/**
	 * Serializes an object.
	 * @param object the object
	 * @return the serialized version
	 */
	public final static <Type> String serialize(Type object) {
		final ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
		final ObjectOutputStream o_os;
		try {
			o_os = new ObjectOutputStream(ba_os);
			o_os.writeObject(object);
			o_os.close();
		} catch (IOException e) {
			warn_serialize(e);
		}
		
		String result = ba_os.toString();
		System.out.println("SERIALIZER RESULT: \""+result+"\"");
		return result;
	}
	/**
	 * Deserializes an object
	 * @param data the serialized data to deserialize
	 * @return a deserialized object instance
	 */
	@SuppressWarnings("unchecked") public final static <Type> Type deserialize(final String data) {
		final InputStream ba_is = new ByteArrayInputStream(data.getBytes());
		final ObjectInputStream o_is;
		Type result = null;
		
		try {
			o_is = new ObjectInputStream(ba_is);
			final Object o = o_is.readObject();
			result = (Type)(o);
		} catch (IOException e) {
			System.err.println("Got IOException");
			warn_serialize(e);
		} catch (ClassNotFoundException e) {
			System.err.println("Got ClassNotFoundException");
			warn_serialize(e);
		}
		
		return result;
	}
}