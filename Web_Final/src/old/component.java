package old;

/*import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs460.webdnd.client.utilities.misc;*/

/**
 * Stores a component (either a tag or some content).
 * @author Ian Mallett
 */
public abstract class component {
	/** The content */
	public String content;
	/** The effect this data has on the indentation of a HTML file */
	public int indent_effect;
	/** The options this component should have applied to it */
	//protected final optionsHTML options;
	
	/**
	 * Called internally to create a new component.  Using the factory pattern.
	 * Parameters are obvious from their names.
	 * @param content
	 * @param options
	 */
	/*protected component(final String content, final optionsHTML options) {
		this.content = content;
		this.options = options;
	}*/
	/**
	 * Returns a new component with parameters derived obviously from the given arguments.
	 * @param content
	 * @param options
	 * @return
	 */
	/*public final static component get_component(final String content, final optionsHTML options) {
		return (content.charAt(0)=='<') ? new component_tag(content,options) : new component_element(content,options);
	}*/
	
	/** Called to attempt to make the component adhere to good HTML practices, because GWT's output is often incorrect. */
	public abstract void fix();
	
	/**
	 * A subtype that is for HTML tags only.
	 * @author Ian Mallett
	 */
	protected final static class component_tag extends component {
		//private boolean is_comment = false;
		
		/**
		 * Called internally to create a new component.  Using the factory pattern.
		 * Parameters are obvious from their names.
		 * @param content
		 * @param options
		 */
		/*protected component_tag(final String content, final optionsHTML options) {
			super(content,options);
			
			if (content.charAt(1)=='/') {
				indent_effect = -1;
			}
			else if (content.startsWith("<!--")&&content.endsWith("-->")) {
				indent_effect = 0;
				is_comment = true;
			}
			else if (content.charAt(content.length()-2)=='/') {
				indent_effect = 0;
			}
			else {
				indent_effect = 1;
			}
		}*/
		
		/** If I could have made this a #define or a lambda expression, I would have. */
		/*private final static void print_tag_error_equals(final String tag) {
			misc.warn(
				"An element of a tag has incorrect \"=\"!\n"+
				"This is a syntax error, and valid HTML cannot be inferred for this tag.\n"+
				"Tag was \"<"+tag+">\"",
				misc.MODULE.MODULE_PARSER
			);
		}*/
		
		/**
		 * Split the tag's (e.g. "img src="hi.png" width=6"; note no brackets) parts into elements
		 * (e.g. "div", "width=100", etc.), being careful of spaces/tabs/etc. around equals.
		 */
		/*private final static ArrayList<String> split_tag(final String tag) {
			//System.out.println("Tag \""+tag+"\"");
			
			final ArrayList<String> result = new ArrayList<String>();
			
			final Pattern pattern = Pattern.compile(
					"([^\\s\\\"\\\'=]+\\s*=\\s*([^\\s\\\"\\\'=]+|[\\\"\\\'][^\\s\\\"\\\']+[\\\"\\\']))|([^\\s\\\"\\\'=]+)"
			);
			final Matcher matcher = pattern.matcher(tag);
			
			int end1=0,start2,end2;
			boolean error = false;
			while (matcher.find()) {
				start2 = matcher.start();
				end2 = matcher.end();
				//System.out.println(start2+" "+end2);
				
				final String element = tag.substring(start2,end2);
				if (tag.substring(end1,start2).trim().length()!=0) error = true;
				
				end1 = end2;
				
				//System.out.println( "Element: \"" + element + "\"" );
				
				result.add(element);
			}
			if (tag.substring(end1,tag.length()).trim().length()!=0) error = true;
			
			if (error) print_tag_error_equals(tag);
			
			return result;
		}
		private final String format_tag(ArrayList<String> elements) {
			//Not as simple as it first seems; the tag may contain case-sensitive strings.
			StringBuilder result = new StringBuilder();
			
			int len = elements.size(); //I do not trust Java to make this O(1).
			for (int i=0;i<len;++i) {
				char[] element_chars = elements.get(i).toCharArray(); //This has better be O(1) + O(n).
				//System.out.println("Got element: \""+element+"\"!");
				boolean found_equals = false;
				for (char c : element_chars) {
					if (c=='=') found_equals = true;
					else if (!found_equals) {
						if (options.UPPERCASE) c = Character.toUpperCase(c);
						else                   c = Character.toLowerCase(c);
					}
					result.append(c);
				}
				if (i!=len-1) result.append(" ");
			}
			
			return result.toString();
		}*/
		
		@Override public void fix() {
			/*if (is_comment) return;
			
			String formatted_inside = format_tag(split_tag(  content.substring(1,content.length()-1)  ));
			
			//This code works around bugs in the GWT HTML exporter, which can't seem to understand that it
			//should write "<input ... />" instead of "<input ... >".  Ditto for <col> and <br>.
			final String lower = formatted_inside.toLowerCase();
			final String[] errors = {"input","col","br"};
			for (String e : errors) {
				if (lower.startsWith(e)) {
					formatted_inside += "/";
					indent_effect = 0;
					break;
				}
			}
			
			content = "<"+formatted_inside+">";*/
		}
	}
	
	/**
	 * A subtype that is for data segments only.
	 * @author Ian Mallett
	 */
	protected final static class component_element extends component {
		/**
		 * Called internally to create a new component.  Using the factory pattern.
		 * Parameters are obvious from their names.
		 * @param content
		 * @param options
		 */
		/*protected component_element(String content, optionsHTML options) {
			super(content,options);
			indent_effect = 0;
		}*/
		
		@Override public void fix() {}
	}
}