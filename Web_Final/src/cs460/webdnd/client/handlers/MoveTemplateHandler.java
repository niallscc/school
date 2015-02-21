package cs460.webdnd.client.handlers;

import com.smartgwt.client.widgets.tab.events.TabDeselectedEvent;
import com.smartgwt.client.widgets.tab.events.TabDeselectedHandler;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.elements.WebPage;

/**
 * This Handler is utilized by Tab Switching to successfully move the Template
 * WebPage into other WebPages, so all webpages can appear to have the template
 * while also being able to be edited separately.
 * 
 * @author Theodore Schnepper
 */
public class MoveTemplateHandler implements TabSelectedHandler, TabDeselectedHandler {
	private WebPage page;
	private CState state;
	private String headerHeight;
	private String bodyHeight;
	private String footerHeight;

	public MoveTemplateHandler(WebPage page, CState state) {
		this.page = page;
		this.state = state;
	}

	@Override
	public void onTabSelected(TabSelectedEvent event) {
		page.UpdateTemplate(state.getTemplate());
		final WebPage template = state.getTemplate();
		if(template == null)
			return;
		headerHeight = template.getHeader().getHeightAsString();
		bodyHeight = template.getBody().getHeightAsString();
		footerHeight = template.getFooter().getHeightAsString();
	}

	@Override
	public void onTabDeselected(TabDeselectedEvent event) {
		this.state.getTemplate().restore();
		final WebPage template = state.getTemplate();
		if(template == null)
			return;
		template.getHeader().setHeight(headerHeight);
		template.getBody().setHeight(bodyHeight);
		template.getFooter().setHeight(footerHeight);
	}
}