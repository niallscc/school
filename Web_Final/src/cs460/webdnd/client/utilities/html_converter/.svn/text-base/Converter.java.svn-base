package cs460.webdnd.client.utilities.html_converter;

import java.util.List;

import cs460.webdnd.client.elements.WebPage;

/**
 * Parses a GWT object into HTML.
 * 
 * @author Ian Mallett
 */
public final class Converter {
	private final static boolean include_template = true;

	private final WebPage webpage, template;
	private final String baseurl;
	private final List<String> css_file_names;

	/**
	 * Makes a new converter object.
	 * 
	 * @param webpage
	 *            the webpage to convert
	 * @param template
	 *            the webpage's template webpage file
	 * @param baseurl
	 *            the base URL of the CSS files to include
	 * @param css_file_names
	 *            the file names of the CSS files
	 */
	public Converter(final WebPage webpage, final WebPage template,
			final String baseurl, final List<String> css_file_names) {
		this.webpage = webpage;
		this.template = template;
		this.css_file_names = css_file_names;

		this.baseurl = baseurl;

		if (webpage == null || template == null) {
			System.err
					.println("WARNING: Argument(s) to parser constructor was null, expecting \".generate()\" to fail!");
		}
	}

	private final String getRawData() {
		final boolean gap = true;

		final int offset_header, offset_body, offset_footer;
		if (gap) {
			offset_header = 10;
			offset_body = 10;
			offset_footer = 10;
		} else {
			offset_header = offset_body = offset_footer = 0;
		}

		ConversionHelpers.reset();
		final String result = ""
				+ "<html>\n"
				+ "\n"
				+
				// ((include_template)?generate_header.generate(template)+"\n":"")
				// +
				GenerateHeader.generate(webpage, baseurl, css_file_names)
				+ "\n"
				+ "<body>\n"
				+ "\n"
				+ "<div align=\"center\">"
				+ "<!--    HEADER-->"
				+ ConversionHelpers.combineTemplateAndPageBlocks(
						((include_template) ? GenerateBody.generate(template
								.getHeader()) + "\n" : ""),
						GenerateBody.generate(webpage.getHeader()),
						offset_header)
				+ "<!--END HEADER-->"
				+ "\n"
				+ "<!--    BODY-->"
				+ ConversionHelpers.combineTemplateAndPageBlocks(
						((include_template) ? GenerateBody.generate(template
								.getBody()) + "\n" : ""),
						GenerateBody.generate(webpage.getBody()), offset_body)
				+
				// canvas.getElement().getInnerHTML() +
				"<!--END BODY-->"
				+ "\n"
				+ "<!--    FOOTER-->"
				+ ConversionHelpers.combineTemplateAndPageBlocks(
						((include_template) ? GenerateBody.generate(template
								.getFooter()) + "\n" : ""),
						GenerateBody.generate(webpage.getFooter()),
						offset_footer)
				+ "<!--END FOOTER-->"
				+ "</div>"
				+ "\n"
				+ "</body>\n"
				+ "\n"
				+ "</html>\n";

		// System.out.println("RAW DATA: \""+result+"\"");
		// System.out.println("RAW DATA: \""+canvas.getElement().getInnerHTML()+"\"");
		return result;
	}

	/**
	 * Executes the conversion. Assumes the default parameters.
	 * 
	 * @return the rendered HTML.
	 */
	public final String generate() {
		return new OptionsHTML().apply(getRawData());
	}

	/**
	 * Executes the conversion with the given parameters.
	 * 
	 * @return the rendered HTML.
	 */
	public final String generate(final OptionsHTML options) {
		return options.apply(getRawData());
	}
}