package cs460.webdnd.client.elements.properties;

import java.util.LinkedHashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SliderItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.commands.SetBorderCommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.mvc.model.IState;
import cs460.webdnd.client.mvc.view.IView;
import cs460.webdnd.client.utilities.Misc;

/**
 * The BordersTab allows a user to edit the look of a border, including its
 * thickness (in pixels), radius (degree of rounding on the corners), type
 * (shape of the border, e.g. "U" shaped, arched, or full), style (e.g. solid,
 * dotted, or dashed), and color (e.g. Crimson, Green, or Olive)
 * 
 * @author John Schulz (jdschulz@cs.unm.edu)
 */
public class BordersTab extends Tab implements IView, IController {
	private IModel model;

	protected Button submit;
	protected Canvas preview;
	protected ListBox borderType;
	protected SliderItem borderWidth;
	protected ListBox borderStyle;
	protected ListBox borderColor;
	protected SliderItem borderRadius;
	protected ChangedHandler borderChange;
	protected ChangeHandler preChange;
	protected String current_style = "";
	protected String old_style = "";

	protected static LinkedHashMap<String,String> borderTypeValues;
	protected static LinkedHashMap<String,String> borderStyleValues;
	protected static LinkedHashMap<String,String> borderColorValues;

	public BordersTab(final IModel model) {
		this.model = model;

		this.setTitle("Borders");
		Canvas temp = new Canvas();
		temp.setHeight(100);
		temp.setWidth(100);
		preview = new Canvas();
		preview.setHeight(50);
		preview.setWidth(50);
		temp.addChild(preview);
		preview.setLeft("25%");
		preview.setTop("25%");

		DynamicForm sliders = new DynamicForm();
		TabSet borderColorSet = new TabSet();
		borderColorSet.setTabBarPosition(Side.TOP);
		borderColorSet.setPosition(Positioning.RELATIVE);
		borderColorSet.setWidth100();
		borderColorSet.setHeight("90%");
		borderColorSet.setTop(0);
		borderColorSet.setLeft(0);

		borderTypeValues = new LinkedHashMap<String, String>();
		borderTypeValues.put("Full", "full");
		borderTypeValues.put("North West", "northWest");
		borderTypeValues.put("north", "North");
		borderTypeValues.put("North East", "northEast");
		borderTypeValues.put("West", "west");
		borderTypeValues.put("East", "east");
		borderTypeValues.put("South West", "southWest");
		borderTypeValues.put("South", "south");
		borderTypeValues.put("South East", "southEast");
		borderTypeValues.put("Top/Bottom", "topBottom");
		borderTypeValues.put("Left/Right", "leftRight");
		borderTypeValues.put("C", "c");
		borderTypeValues.put("Reverse C", "reverseC");
		borderTypeValues.put("U", "u");
		borderTypeValues.put("Arch", "arch");

		borderType = new ListBox();
		borderType.setWidth("100px");
		borderType.setHeight("20px");
		borderType.setTitle("Border Type:");
		for (String s : borderTypeValues.keySet()) {
			borderType.addItem(s);
		}

		borderWidth = new SliderItem();
		borderWidth.setMinValue(0);
		borderWidth.setHeight("20px");
		borderWidth.setTitle("Border Width:");

		borderStyleValues = new LinkedHashMap<String,String>();
		borderStyleValues.put("None", "none");
		borderStyleValues.put("Hidden", "hidden");
		borderStyleValues.put("Dotted", "dotted");
		borderStyleValues.put("Dashed", "dashed");
		borderStyleValues.put("Solid", "solid");
		borderStyleValues.put("Double", "double");
		borderStyleValues.put("Groove", "groove");
		borderStyleValues.put("Ridge", "ridge");
		borderStyleValues.put("Inset", "inset");
		borderStyleValues.put("Outset", "outset");

		borderStyle = new ListBox();
		borderStyle.setWidth("100px");
		borderStyle.setHeight("20px");
		borderStyle.setTitle("Border Style:");
		for (String s : borderStyleValues.keySet()) {
			borderStyle.addItem(s);
		}

		borderColor = new ListBox();
		borderColor.setWidth("100px");
		borderColor.setHeight("20px");
		borderColor.setTitle("Border Color:");
		borderColorValues = new CSSColorList();
		for (String s : borderColorValues.keySet()) {
			borderColor.addItem(s);
		}

		borderRadius = new SliderItem();
		borderRadius.setMinValue(0);
		borderRadius.setHeight("20px");
		borderRadius.setTitle("Border Radius:");

		VStack stack = new VStack();

		this.setPane(stack);

		stack.addMember(temp);

		sliders.setItems(borderWidth, borderRadius);

		stack.addMember(sliders);

		{
			Canvas l;
			{
				l = new Canvas();
				l.setWidth100();
				l.setHeight(15);
				l.setContents("<b>Border Type:</b>");
				stack.addMember(l);
				stack.addMember(borderType);
			}
			{
				l = new Canvas();
				l.setWidth100();
				l.setHeight(15);
				l.setContents("<b>Border Style:</b>");
				stack.addMember(l);
				stack.addMember(borderStyle);
			}
			{
				l = new Canvas();
				l.setWidth100();
				l.setHeight(15);
				l.setContents("<b>Border Color:</b>");
				stack.addMember(l);
				stack.addMember(borderColor);
			}
		}
		
		BoxChangeHandler handle = new BoxChangeHandler(borderColor,
				borderStyle, borderType, borderWidth, borderRadius, preview);
		borderType.addChangeHandler(handle);
		borderColor.addChangeHandler(handle);
		borderStyle.addChangeHandler(handle);
		borderWidth.addChangeHandler(handle);
		borderRadius.addChangeHandler(handle);

		submit = new Button("Submit");
		stack.addMember(submit);
		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CState state = Misc.getCState(model);
				if (state == null)
					return;

				Canvas sel = state.getSelection();
				if (sel == null)
					return;

				model.run(new SetBorderCommand(sel, preview.getStyleName()));

			}

		});
	}

	@Override
	public void registerModel(IModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	/**
	 * Updates the elements of BordersTab corresponding to the border style of
	 * the selected canvas to reflect the selected canvas.
	 */
	@Override
	public void Update(IState state) {
		CState cstate = Misc.getCState(model);
		if (state == null)
			return;

		Canvas cIn = cstate.getSelection();
		if (cIn == null) {
			submit.setEnabled(false);
			return;
		}

		String oldStyle = cIn.getStyleName();

		parse(oldStyle);
		submit.setEnabled(true);
	}

	/**
	 * Used to parse out all the details of a canvas' style so that the
	 * boxes/sliders in BordersTab reflect a canvas's style when selected.
	 * 
	 * @param str
	 */
	private void parse(String str) {
		String[] s = str.replace('-', ' ').split(" ");
		String dType = "Full";
		String dStyle = "None";
		String dColor = "Black";
		float dWidth = 1.0f;
		float dRadius = 1.0f;

		for (int i = 0; i < s.length; i++) {
			if (borderStyleValues.containsValue(s[i])) {
				for (String k : borderStyleValues.keySet()) {
					if (borderStyleValues.get(k).equals(s[i])) {
						dStyle = k;
						break;
					}
				}
			} else if (borderTypeValues.containsValue(s[i])) {
				for (String k : borderTypeValues.keySet()) {
					if (borderTypeValues.get(k).equals(s[i])) {
						dType = k;
						break;
					}
				}
			} else if (borderColorValues.containsValue(s[i])) {
				for (String k : borderColorValues.keySet()) {
					if (borderColorValues.get(k).equals(s[i])) {
						dColor = k;
						break;
					}
				}
			} else {
				if (s[i].charAt(0) == 'r') {
					dRadius = Integer.parseInt(s[i].substring(1,
							s[i].length() - 2));
				} else if (s[i].charAt(0) == 'w') {
					dWidth = Integer.parseInt(s[i].substring(1,
							s[i].length() - 2));
				}
			}
		}

		borderWidth.setValue(dWidth);
		borderRadius.setValue(dRadius);
		for (int i = 0; i < borderStyle.getItemCount(); i++) {
			if (dStyle.equals(borderStyle.getValue(i))) {
				borderStyle.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < borderType.getItemCount(); i++) {
			if (dType.equals(borderType.getValue(i))) {
				borderType.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < borderColor.getItemCount(); i++) {
			if (dColor.equals(borderColor.getValue(i))) {
				borderColor.getValue(i);
				break;
			}
		}
	}

	/**
	 * Monitors changes in the values for the border attributes and sets the
	 * preview canvas up to display the border style before you apply it to the
	 * selected canvas.
	 * 
	 * @author John Schulz (jdschulz@cs.unm.edu)
	 */
	private class BoxChangeHandler implements
			com.google.gwt.event.dom.client.ChangeHandler, ChangeHandler {

		private ListBox borderColor;
		private ListBox borderStyle;
		private ListBox borderType;
		private SliderItem borderWidth;
		private SliderItem borderRadius;
		private Canvas preview;

		public BoxChangeHandler(ListBox bc, ListBox bs, ListBox bt,
				SliderItem bw, SliderItem br, Canvas preview) {
			this.borderColor = bc;
			this.borderStyle = bs;
			this.borderType = bt;
			this.borderWidth = bw;
			this.borderRadius = br;
			this.preview = preview;
		}

		private void apply() {
			StringBuilder sb = new StringBuilder();
			sb.append("bc");
			sb.append(borderColorValues.get(borderColor.getValue(borderColor
					.getSelectedIndex())));
			sb.append(" ");

			sb.append(borderTypeValues.get(borderType.getValue(borderType
					.getSelectedIndex())));
			sb.append("-");
			sb.append(borderStyleValues.get(borderStyle.getValue(borderStyle
					.getSelectedIndex())));
			sb.append(" ");

			sb.append("w");
			sb.append(borderWidth.getValueAsFloat().intValue());
			sb.append("px ");
			sb.append("r");
			sb.append(borderRadius.getValueAsFloat().intValue());
			sb.append("px");

			preview.setStyleName(sb.toString());
		}

		@Override
		public void onChange(com.google.gwt.event.dom.client.ChangeEvent event) {
			apply();
		}

		@Override
		public void onChange(ChangeEvent event) {
			apply();
		}
	}
}