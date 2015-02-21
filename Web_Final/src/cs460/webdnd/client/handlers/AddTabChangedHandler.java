package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.model.IModel;

public class AddTabChangedHandler implements ClickHandler {
	private Tab selectedTab;
	private TabSet topTabSet;
	private CState state;

	public AddTabChangedHandler(IModel model, TabSet topTabSet) {
		selectedTab = topTabSet.getSelectedTab();
		this.topTabSet = topTabSet;
		state = (CState) model.getState();
	}

	@Override
	public void onClick(ClickEvent event) {
		if (selectedTab == null) {
			selectedTab = topTabSet.getSelectedTab();
		}

		if (!selectedTab.equals(topTabSet.getSelectedTab())) {
			selectedTab = topTabSet.getSelectedTab();
			state.setCurrentPane(selectedTab.getPane());
			state.setSelection(null);
		}
	}
}