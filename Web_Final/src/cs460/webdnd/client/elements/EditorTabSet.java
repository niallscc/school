package cs460.webdnd.client.elements;

import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.elements.properties.BackgndColorImgTab;
import cs460.webdnd.client.elements.properties.BordersTab;
import cs460.webdnd.client.mvc.model.IModel;

public class EditorTabSet extends TabSet {
	protected BordersTab bt;
	protected BackgndColorImgTab bgcit;

	public EditorTabSet(final IModel model) {
		bt = new BordersTab(model);
		bgcit = new BackgndColorImgTab(model);

		this.addTab(bt);
		this.addTab(bgcit);

		model.addView(bt);
		model.addView(bgcit);

		this.setTop("5%");
		this.setLeft("80%");
		this.setHeight("95%");
		this.setWidth("250");
	}
}