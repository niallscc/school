package cs460.webdnd.client.utilities.serialization;

import java.util.LinkedList;

import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.elements.menuStyles.NavMenu;
import cs460.webdnd.client.mvc.controller.IController;

/**
 * A hugely kludgey workaround to Java's broken serializer and GWT's poor
 * design.
 * 
 * I wrote this. I'm proud of it in the same way one is about a massacred horde
 * of adorable baby zombie penguins--they had to be dispatched for the greater
 * good, but I'll always feel somewhat sick to my stomach thinking about it.
 * 
 * @author Ian Mallett
 */
public final class SerializerCanvas {
	private static StringBuilder result;

	private static int i;

	private final static String _break = "__BREAK__";
	private final static String _left = "LEFT"; // Can't use "[", "]", because
												// when these are used for
												// splitting, regexes are used.
	private final static String _right = "RIGHT";
	private final static String _menu = "MENU";
	private final static String _canvas = "CANVAS";

	private static IController controller = null;

	public final static void setIController(IController controller) {
		SerializerCanvas.controller = controller;
	}

	// These functions pack a given datatype into a String or add it into the
	// result.
	private final static void add(String s) {
		result.append(s);
		result.append(_break);
	}

	private final static void pack(final Boolean data) {
		add(data == null?"null":(data?"1":"0"));
	}

	private final static void pack(final Integer data) {
		add(data == null?"null":data.toString());
	}

	private final static void pack(final String data) {
		add(data);
	}

	// These functions unpack a String into datatypes, or get the next logical
	// String block.
	private final static String get(final String[] blocks) {
		return blocks[i++];
	}

	private final static Boolean unpackBoolean(final String[] blocks) {
		final String temp = get(blocks);
		return temp.equals("null")?null:Boolean.parseBoolean(temp);
	}

	private final static Integer unpackInteger(final String[] blocks) {
		final String temp = get(blocks);
		return temp.equals("null")?null:Integer.parseInt(temp);
	}

	private final static String unpackString(final String[] blocks) {
		final String temp = get(blocks);
		return temp.equals("null")?null:temp;
	}

	// Serialization functions
	private final static <T extends Canvas> void serializeHelperChildren(final T object) {
		for (Canvas c : object.getChildren()) {
			add(_left);
			serializeHelper(c);
			add(_right);
		}
	}

	private final static <T extends Canvas> void serializeHelper(final T object) {
		// Check what type the object is. If it's a menu, do special handling.
		if (object instanceof NavMenu) {
			final String style_name = object.getStyleName();
			System.out.println("serializing Menu");
			pack(_menu);
			pack(style_name);
			return;
		}
		System.out.println("serializing Canvas");
		pack(_canvas);

		pack(object.getBackgroundColor());
		pack(object.getBackgroundImage());
		pack(object.getBackgroundPosition());
		pack(object.getCanFocus());
		pack(object.getCanHover());
		pack(object.getCanSelectText());

		serializeHelperChildren(object);

		pack(object.getContents());
		pack(object.getEdgeMarginSize());
		pack(object.getHeightAsString());
		pack(object.getLeftAsString());
		pack(object.getMargin());
		pack(object.getMaxHeight());
		pack(object.getMaxWidth());
		pack(object.getMinHeight());
		pack(object.getMinWidth());
		pack(object.getOpacity());
		pack(object.getPadding());
		pack(object.getScClassName());
		pack(object.getScrollbarSize());
		pack(object.getShowResizeBar());
		pack(object.getStyleName());
		pack(object.getTitle());
		pack(object.getTopAsString());
		pack(object.getWidthAsString());
	}

	/**
	 * Call this function to serialize the important parts of a Canvas object
	 * (or subclass thereof).
	 * 
	 * @param object the Canvas-like object
	 * @return the serialized data
	 */
	public final static <T extends Canvas> String serialize(final T object) {
		result = new StringBuilder();
		serializeHelper(object);
		return result.toString();
	}

	// Deserialization functions
	private final static Canvas[] deserializeHelperChildren(final String[] blocks) {
		// System.out.println(".deserialize_helper_children(...), with blocks: \""+Arrays.toString(blocks)+"\"!");

		final LinkedList<Canvas> children = new LinkedList<Canvas>();

		// List of childrens' blocks.
		final LinkedList<LinkedList<String>> childrens_blocks_list = new LinkedList<LinkedList<String>>();

		int depth = 0;
		LinkedList<String> current_childs_blocks = null;
		while (true) {
			final String temp = get(blocks);
			// System.out.println("Loop got: \""+temp+"\".");

			if (temp.equals(_left)) {
				if (depth == 0) {
					current_childs_blocks = new LinkedList<String>();
				} else {
					current_childs_blocks.add(temp);
				}
				++depth;
			} else if (temp.equals(_right)) {
				--depth;
				if (depth == 0) {
					childrens_blocks_list.add(current_childs_blocks);
					current_childs_blocks = null;
				} else {
					current_childs_blocks.add(temp);
				}
			} else {
				if (current_childs_blocks == null) { // there were no (more)
														// children!
					--i;
					break;
				}
				current_childs_blocks.add(temp);
			}
		}
		if (childrens_blocks_list.size() == 0) return null;

		final int temp_i = i;
		// int j = 0;
		for (final LinkedList<String> child_blocks : childrens_blocks_list) {
			i = 0;
			final Canvas child = new Canvas();

			final Object[] temp = child_blocks.toArray();
			final String[] subblocks = new String[temp.length];
			for (int k = 0; k < temp.length; ++k)
				subblocks[k] = (String)(temp[k]);

			// System.out.println(j+"th child's blocks (starting to deserialize):   \""+Arrays.toString(subblocks)+"\"!");
			deserializeHelper(subblocks,child);
			// System.out.println(j+"th child's blocks deserialized!");
			// ++j;
			children.add(child);
		}
		i = temp_i;

		final Object[] temp1 = children.toArray();
		final Canvas[] children_canvases = new Canvas[temp1.length];
		for (int k = 0; k < temp1.length; ++k)
			children_canvases[k] = (Canvas)(temp1[k]);
		return children_canvases;
	}

	private final static <T extends Canvas> void deserializeHelper(final String[] blocks, final T result) {
		// System.out.println(".deserialize_helper(...),          with blocks: ""+Arrays.toString(blocks)+""!");
		// System.out.println(".deserialize_helper(...),          with blocks: ""+Arrays.toString(blocks)+""!");
		{
			final String type = unpackString(blocks);
			if (type.equals(_menu)) {
				System.out.println("Deserializing Menu");
				final NavMenu menu = new NavMenu(unpackString(blocks),controller);
				controller.getModel().addView(menu);
				result.addChild(menu);
				return;
			}
		}
		System.out.println("Deserializing Canvas");

		String temp;
		if ((temp = unpackString(blocks)) != null) result.setBackgroundColor(temp);
		if ((temp = unpackString(blocks)) != null) result.setBackgroundImage(temp);
		if ((temp = unpackString(blocks)) != null) result.setBackgroundPosition(temp);
		result.setCanFocus(unpackBoolean(blocks));
		result.setCanHover(unpackBoolean(blocks));
		result.setCanSelectText(unpackBoolean(blocks));

		{
			final Canvas[] children = deserializeHelperChildren(blocks);
			if (children != null) {
				// result.setChildren(children);
				// result.setChildren(children);
				for (Canvas child : children) {
					result.addChild(child);
				}
			}
		}

		if ((temp = unpackString(blocks)) != null) result.setContents(temp);
		result.setEdgeMarginSize(unpackInteger(blocks));
		if ((temp = unpackString(blocks)) != null) result.setHeight(temp);
		if ((temp = unpackString(blocks)) != null) result.setLeft(temp);
		result.setMargin(unpackInteger(blocks));
		result.setMaxHeight(unpackInteger(blocks));
		result.setMaxWidth(unpackInteger(blocks));
		result.setMinHeight(unpackInteger(blocks));
		result.setMinWidth(unpackInteger(blocks));
		result.setOpacity(unpackInteger(blocks));
		result.setPadding(unpackInteger(blocks));
		if ((temp = unpackString(blocks)) != null) result.setScClassName(temp);
		result.setScrollbarSize(unpackInteger(blocks));
		result.setShowResizeBar(unpackBoolean(blocks));
		if ((temp = unpackString(blocks)) != null) result.setStyleName(temp);
		if ((temp = unpackString(blocks)) != null) result.setTitle(temp);
		if ((temp = unpackString(blocks)) != null) result.setTop(temp);
		if ((temp = unpackString(blocks)) != null) result.setWidth(temp);
	}

	/**
	 * Call this function to deserialize from a String the important parts of a
	 * new Canvas object.
	 * 
	 * @param serialized_canvas_str the serialized data
	 * @param result the Canvas-like object to have its fields set
	 */
	public final static <T extends Canvas> void deserialize(final String serialized_canvas_str, final T result) {
		// System.out.println("BEGINNING DESERIALIZE!");
		i = 0;
		deserializeHelper(serialized_canvas_str.split(_break),result);
		// System.out.println("FINISHED DESERIALIZE!");
	}
}