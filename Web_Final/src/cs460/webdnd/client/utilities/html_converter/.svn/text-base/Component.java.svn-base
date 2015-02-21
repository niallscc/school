package cs460.webdnd.client.utilities.html_converter;

/**
 * Stores a component (either a tag or some content).
 * 
 * @author Ian Mallett
 */
public abstract class Component {
	/** The content */
	public String content;
	/** The effect this data has on the indentation of a HTML file */
	public int indent_effect;
	/** The options this component should have applied to it */
	protected final OptionsHTML options;

	/**
	 * Called internally to create a new component. Using the factory pattern.
	 * Parameters are obvious from their names.
	 * 
	 * @param content
	 * @param options
	 */
	protected Component(final String content, final OptionsHTML options) {
		this.content = content;
		this.options = options;
	}

	/**
	 * Returns a new component with parameters derived obviously from the given
	 * arguments.
	 * 
	 * @param content
	 * @param options
	 * @return
	 */
	public final static Component getComponent(final String content,
			final OptionsHTML options) {
		return (content.charAt(0) == '<') ? new ComponentTag(content, options)
				: new ComponentElement(content, options);
	}

	/**
	 * Called to attempt to make the component adhere to good HTML practices,
	 * because GWT's output is often incorrect.
	 */
	public abstract void fix();

	/**
	 * A subtype that is for HTML tags only.
	 * 
	 * @author Ian Mallett
	 */
	protected final static class ComponentTag extends Component {
		/**
		 * Called internally to create a new component. Using the factory
		 * pattern. Parameters are obvious from their names.
		 * 
		 * @param content
		 * @param options
		 */
		protected ComponentTag(final String content, final OptionsHTML options) {
			super(content, options);

			if (content.charAt(1) == '/') {
				indent_effect = -1;
			} else if (content.startsWith("<!--") && content.endsWith("-->")) {
				indent_effect = 0;
			} else if (content.charAt(content.length() - 2) == '/') {
				indent_effect = 0;
			} else {
				indent_effect = 1;
			}
		}

		@Override
		public void fix() {
		}
	}

	/**
	 * A subtype that is for data segments only.
	 * 
	 * @author Ian Mallett
	 */
	protected final static class ComponentElement extends Component {
		/**
		 * Called internally to create a new component. Using the factory
		 * pattern. Parameters are obvious from their names.
		 * 
		 * @param content
		 * @param options
		 */
		protected ComponentElement(String content, OptionsHTML options) {
			super(content, options);
			indent_effect = 0;
		}

		@Override
		public void fix() {
		}
	}
}