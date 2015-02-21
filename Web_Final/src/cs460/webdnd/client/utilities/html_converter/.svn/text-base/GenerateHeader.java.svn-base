package cs460.webdnd.client.utilities.html_converter;

import java.util.List;

import cs460.webdnd.client.elements.WebPage;

/**
 * Generates a HTML header.
 * 
 * @author Ian Mallett
 */
public final class GenerateHeader {
	/**
	 * Generates the header for a page.
	 * 
	 * @param page
	 *            the webpage
	 * @param baseurl
	 *            the base URL of the CSS files
	 * @param css_file_names
	 *            the CSS files' names
	 * @return the generated header
	 */
	public final static String generate(final WebPage page,
			final String baseurl, final List<String> css_file_names) {
		StringBuilder css_string = new StringBuilder();
		for (String css_file_name : css_file_names) {
			css_string
					.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
			css_string.append(baseurl);
			css_string.append(css_file_name);
			css_string.append("\"></link>");
		}
		return "" + "<head>\n" + getMeta() + "<title>" + page.getName()
				+ "</title>\n" +
				// Can't change the following to the form: "<.../>" because of a
				// bug in Chrome.
				css_string.toString() + "</head>\n";
	}

	/** TODO:? */
	private final static String getMeta() {
		// TODO:
		return "";
		// helpers.get_indent(1)+"<meta name=\"keywords\" content=\"\"/>\n" +
		// helpers.get_indent(1)+"<meta name=\"author\" content=\"\"/>\n" +
		// helpers.get_indent(1)+"<meta name=\"description\" content=\"\"/>\n" +
		// helpers.get_indent(1)+"<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"".$rel_prefix."page/style.php\"/>\n"
		// +
	}
}