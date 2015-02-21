package cs460.webdnd.client.utilities.html_converter;

import java.util.LinkedList;

import cs460.webdnd.client.utilities.Misc;

/**
 * Options for converting HTML. Pass an instance of this class to
 * parser.generate().
 * 
 * @author Ian Mallett
 */
public final class OptionsHTML {
	/**
	 * Change as desired. If true, tags and keywords will be made uppercase. If
	 * false, lowercase. IDs (e.g. the "HI" in "<div class=HI>") will NOT be
	 * changed.
	 */
	public boolean UPPERCASE = false;

	/**
	 * Change as desired. If true, HTML will be indented according to
	 * helpers.indent_type.
	 */
	public boolean WITH_INDENT = true;

	/** Change as desired. If true, HTML will be outputted with newlines. */
	public boolean WITH_NEWLINES = true;

	/**
	 * Change as desired. If true, HTML will be outputted with pipe ("|")
	 * characters to show nesting depth. Only has an effect if WITH_INDENT is
	 * true.
	 */
	public boolean WITH_PIPES = false;

	/** Your options for the indentation type */
	public static enum INDENT_TYPE {
		TAB("\t"), SPACE(" "), SPACE2("  "), SPACE4("    "), SPACE8("        ");
		private INDENT_TYPE(final String text) {
			this.text = text;
		}

		@Override
		public final String toString() {
			return text;
		}

		private final String text;
	}

	/** Change as desired. */
	public INDENT_TYPE indent_type = INDENT_TYPE.SPACE2;

	private final String getIndent(final int depth) {
		String result = "";
		if (WITH_PIPES) {
			for (int i = 0; i < depth; ++i)
				result += "|" + indent_type;
		} else {
			for (int i = 0; i < depth; ++i)
				result += indent_type;
		}
		return result;
	}

	// Algorithm for applying:
	// 1. break ("bust") the data string up into tags and elements (e.g. tags:
	// "<div>", "</form>", etc. e.g. elements: "Hello my name is Bob.")
	// 2. Fix the tags
	// 3. Format everything
	// 4. Return

	private final LinkedList<Component> bust(final String raw_data) {
		// Break data into tags and elements

		final LinkedList<Component> result = new LinkedList<Component>();

		{
			final LinkedList<Component> result2 = new LinkedList<Component>();

			StringBuilder current = new StringBuilder();
			final byte[] data = raw_data.getBytes();
			for (final byte b : data) {
				if (current.length() != 0) {
					if (b == '<') {
						result2.addLast(Component.getComponent(
								current.toString(), this));
						current = new StringBuilder("<");
					} else if (b == '>') {
						current.append((char) (b));
						result2.addLast(Component.getComponent(
								current.toString(), this));
						current = new StringBuilder();
					} else
						current.append((char) (b));
				} else
					current.append((char) (b));
			}
			if (current.length() != 0)
				result2.addLast(Component.getComponent(current.toString(), this));

			for (final Component s : result2) {
				s.content = s.content.trim();
				if (s.content.length() != 0)
					result.add(s);
			}
		}

		// for (component c : result)
		// System.out.println("Got component \""+c.content+"\"");

		return result;
	}

	private final static LinkedList<Component> fixTags(
			final LinkedList<Component> components) {
		// Fix known errors with GWT's HTML generation.

		for (final Component c : components) {
			c.fix();
			// System.out.println("Fixed component \""+c.content+"\"");
		}

		return components;
	}

	private final String format(final LinkedList<Component> components) {
		// Format the tags and elements with indentation, cases, etc. Put it all
		// in one string and return.
		final StringBuilder result = new StringBuilder();

		int indent_level = 0;
		for (final Component c : components) {
			String line;

			if (c.indent_effect < 0)
				indent_level += c.indent_effect;

			if (WITH_INDENT)
				line = getIndent(indent_level) + c.content;
			else
				line = c.content;

			if (WITH_NEWLINES)
				line += "\n";

			result.append(line);

			if (c.indent_effect > 0)
				indent_level += c.indent_effect;
		}

		if (indent_level != 0)
			Misc.warn(
					"Indentation was nonzero.  Either the HTML is malformed, or GWT has fixed/broken some of their HTML problems.",
					Misc.MODULE.MODULE_PARSER);

		return result.toString();
	}

	/**
	 * Do not call this! The parser will do it. If Java had friend declarations,
	 * this would be private.
	 */
	public final String apply(final String data) {
		if (data.equals(""))
			return "";
		return format(fixTags(bust(data)));
	}
}